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

if (!isset($input['balance'])) {
    http_response_code(400);
    echo json_encode(["error" => "Missing balance parameter"]);
    exit;
}

$balance = floatval($input['balance']);

try {
    // Update the balance for the primary account
    $stmt = $pdo->prepare("UPDATE accounts SET balance = :balance WHERE id = 1");
    $stmt->execute(['balance' => $balance]);
    
    echo json_encode(["success" => true, "message" => "Balance updated successfully"]);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Database error: " . $e->getMessage()]);
}
?>
