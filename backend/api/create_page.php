<?php
header('Content-Type: application/json');

// Get JSON POST payload
$json = file_get_contents('php://input');
$data = json_decode($json, true);

if (!$data) {
    echo json_encode(["status" => "error", "message" => "Invalid JSON payload"]);
    exit;
}

$fieldsJsonString = $data['fields'] ?? '';
$fields = json_decode($fieldsJsonString, true);

if (!is_array($fields) || empty($fields)) {
    echo json_encode(["status" => "error", "message" => "Fields are required and must be an array"]);
    exit;
}

$page_id = "page_" . uniqid();
$page_title = $data['title'] ?? "Custom Page";

$children = [];
$children[] = [
    "type" => "text",
    "properties" => [
        "text" => $page_title,
        "style" => "titleLarge",
        "padding" => 16
    ]
];

foreach ($fields as $field) {
    $children[] = [
        "type" => $field['type'] ?? 'input_text',
        "properties" => [
            "id" => $field['id'],
            "label" => $field['label']
        ]
    ];
}

$children[] = [
    "type" => "submit_button",
    "properties" => [
        "label" => "Submit " . $page_title,
        "form_id" => "form_" . $page_id
    ],
    "actions" => [
        "onClick" => [
            "type" => "submit_form",
            "payload" => [
                "action" => "submit_dynamic_data",
                "page_id" => $page_id
            ]
        ]
    ]
];

$ui_json = [
    "id" => $page_id,
    "title" => $page_title,
    "content" => [
        "type" => "lazy_column",
        "children" => $children
    ]
];

$ui_json_string = json_encode($ui_json);

require '../db.php'; // Ensure db.php is accessible and sets up $pdo

try {
    $stmt = $pdo->prepare("INSERT INTO dynamic_pages (page_id, title, ui_json) VALUES (:page_id, :title, :ui_json)");
    $stmt->execute([
        'page_id' => $page_id,
        'title' => $page_title,
        'ui_json' => $ui_json_string
    ]);
    
    // Generate and execute CREATE TABLE query
    $table_name = "data_" . $page_id;
    $columns = ["id INT AUTO_INCREMENT PRIMARY KEY", "user_id INT DEFAULT NULL"];
    foreach ($fields as $field) {
        $col_name = preg_replace('/[^a-zA-Z0-9_]/', '', $field['id'] ?? '');
        if (!empty($col_name)) {
            $columns[] = "`$col_name` TEXT DEFAULT NULL";
        }
    }
    
    $create_table_sql = "CREATE TABLE IF NOT EXISTS `$table_name` (" . implode(", ", $columns) . ")";
    $pdo->exec($create_table_sql);
    
    echo json_encode(["status" => "success", "page_id" => $page_id]);
} catch (PDOException $e) {
    echo json_encode(["status" => "error", "message" => "Database error: " . $e->getMessage()]);
}
?>
