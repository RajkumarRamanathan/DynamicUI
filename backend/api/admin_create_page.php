<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
require_once '../db.php';

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(["error" => "Method Not Allowed"]);
    exit;
}

$input = json_decode(file_get_contents('php://input'), true);

if (!isset($input['page_id']) || !isset($input['title']) || !isset($input['content'])) {
    http_response_code(400);
    echo json_encode(["error" => "Missing required parameters"]);
    exit;
}

$page_id = strtolower(trim($input['page_id']));
$title = trim($input['title']);
$content_text = trim($input['content']);

// Construct SDUI Screen JSON
$screen = [
    "id" => $page_id,
    "title" => $title,
    "content" => [
        "type" => "column",
        "children" => [
            [
                "type" => "text",
                "properties" => [
                    "text" => $content_text,
                    "style" => "bodyLarge",
                    "padding" => 16
                ]
            ]
        ]
    ]
];

$ui_json = json_encode($screen);

try {
    $stmt = $pdo->prepare("INSERT INTO dynamic_pages (page_id, title, ui_json) VALUES (:page_id, :title, :ui_json) ON DUPLICATE KEY UPDATE title = VALUES(title), ui_json = VALUES(ui_json)");
    $stmt->execute([
        'page_id' => $page_id,
        'title' => $title,
        'ui_json' => $ui_json
    ]);
    
    echo json_encode(["success" => true, "message" => "Page created successfully"]);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Database error: " . $e->getMessage()]);
}
?>
