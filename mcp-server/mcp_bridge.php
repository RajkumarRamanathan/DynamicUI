<?php
// Simple API bridge for the local MCP Server
require_once '../db.php';

header('Content-Type: application/json');

// Super simple security token (in a real app, use something stronger!)
$secret_token = "mcp_secret_12345";
if (!isset($_GET['token']) || $_GET['token'] !== $secret_token) {
    http_response_code(403);
    echo json_encode(["error" => "Unauthorized"]);
    exit;
}

$action = $_GET['action'] ?? '';

try {
    if ($action === 'list_dynamic_pages') {
        $stmt = $pdo->query("SELECT id, page_id, title FROM dynamic_pages");
        $rows = $stmt->fetchAll();
        echo json_encode($rows);
    } 
    elseif ($action === 'get_page_json') {
        $page_id = $_GET['page_id'] ?? '';
        $stmt = $pdo->prepare("SELECT ui_json FROM dynamic_pages WHERE page_id = :page_id");
        $stmt->execute(['page_id' => $page_id]);
        $row = $stmt->fetch();
        if ($row) {
            echo json_encode([$row]);
        } else {
            echo json_encode([]);
        }
    } 
    elseif ($action === 'clear_ai_cache') {
        $stmt = $pdo->prepare("DELETE FROM dynamic_pages WHERE page_id LIKE 'ai_%'");
        $stmt->execute();
        echo json_encode(["affectedRows" => $stmt->rowCount()]);
    } 
    else {
        http_response_code(400);
        echo json_encode(["error" => "Unknown action"]);
    }
} catch (Exception $e) {
    http_response_code(500);
    echo json_encode(["error" => $e->getMessage()]);
}
?>
