<?php
require_once '../db.php';
$stmt = $pdo->prepare("DELETE FROM dynamic_pages WHERE page_id LIKE 'ai_%'");
$stmt->execute();
echo "AI Cache Cleared!";
?>
