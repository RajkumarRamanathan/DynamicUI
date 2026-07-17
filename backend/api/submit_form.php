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

if (!isset($input['formData'])) {
    http_response_code(400);
    echo json_encode(["error" => "Missing formData parameter"]);
    exit;
}

$formData = $input['formData'];
$formId = isset($input['form_id']) ? $input['form_id'] : 'default_admin_form';
$userName = isset($input['user_name']) ? $input['user_name'] : 'Admin';
$action = isset($input['action']) ? $input['action'] : '';
$pageId = isset($input['page_id']) ? $input['page_id'] : '';
$formDataJson = json_encode($formData);

try {
    if ($action === 'submit_dynamic_data' && !empty($pageId)) {
        $table_name = "data_" . $pageId;
        
        $stmt = $pdo->prepare("SELECT id FROM `$table_name` WHERE user_id = 1");
        $stmt->execute();
        $row = $stmt->fetch();
        
        $columns = [];
        $values = [];
        $placeholders = [];
        
        foreach ($formData as $k => $v) {
            $clean_k = preg_replace('/[^a-zA-Z0-9_]/', '', $k);
            if (!empty($clean_k)) {
                $columns[] = "`$clean_k`";
                $values[':'.$clean_k] = is_array($v) ? json_encode($v) : $v;
                $placeholders[] = ':'.$clean_k;
            }
        }
        
        if ($row) {
            $setClause = [];
            foreach ($columns as $col) {
                $clean_col = str_replace('`', '', $col);
                $setClause[] = "$col = :$clean_col";
            }
            $sql = "UPDATE `$table_name` SET " . implode(", ", $setClause) . " WHERE user_id = 1";
            $updateStmt = $pdo->prepare($sql);
            $updateStmt->execute($values);
        } else {
            $columns[] = "`user_id`";
            $placeholders[] = ":user_id";
            $values[':user_id'] = 1;
            
            $sql = "INSERT INTO `$table_name` (" . implode(", ", $columns) . ") VALUES (" . implode(", ", $placeholders) . ")";
            $insertStmt = $pdo->prepare($sql);
            $insertStmt->execute($values);
        }
        
        echo json_encode(["success" => true, "message" => "Dynamic data saved successfully"]);
        exit;
    }

    // Create dynamic_responses table if it doesn't exist
    $createTableSql = "CREATE TABLE IF NOT EXISTS dynamic_responses (
        id INT AUTO_INCREMENT PRIMARY KEY,
        form_id VARCHAR(255) NOT NULL,
        user_name VARCHAR(255) NOT NULL,
        form_data JSON NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    )";
    $pdo->exec($createTableSql);

    // Insert the response
    $stmt = $pdo->prepare("INSERT INTO dynamic_responses (form_id, user_name, form_data) VALUES (:form_id, :user_name, :form_data)");
    $stmt->execute([
        'form_id' => $formId,
        'user_name' => $userName,
        'form_data' => $formDataJson
    ]);

    // Handle specific form actions if necessary based on formId
    // For example, if it's the admin form, we could update the balance table dynamically
    if ($formId === 'admin_balance_update' && isset($formData['new_balance'])) {
        $balance = floatval($formData['new_balance']);
        $updateStmt = $pdo->prepare("UPDATE accounts SET balance = :balance WHERE id = 1");
        $updateStmt->execute(['balance' => $balance]);
    }
    
    echo json_encode(["success" => true, "message" => "Form submitted successfully", "id" => $pdo->lastInsertId()]);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Database error: " . $e->getMessage()]);
}
?>
