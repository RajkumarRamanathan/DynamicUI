<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
require_once '../db.php';

header('Content-Type: application/json');

$prompt = $_GET['prompt'] ?? '';
if (empty($prompt)) {
    http_response_code(400);
    echo json_encode(["error" => "Prompt is required"]);
    exit;
}

// Create a unique page_id based on the prompt (slugify)
$page_id = "ai_" . md5(strtolower(trim($prompt)));

// 1. Check Cache
$stmt = $pdo->prepare("SELECT ui_json FROM dynamic_pages WHERE page_id = :page_id");
$stmt->execute(['page_id' => $page_id]);
$cached_page = $stmt->fetch();

if ($cached_page) {
    echo $cached_page['ui_json'];
    exit;
}

// 2. Call Gemini API
// In production, load this from an environment variable or config.php which is gitignored
$api_key_safe = getenv('GEMINI_API_KEY') ?: 'YOUR_GEMINI_API_KEY';
$url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" . $api_key_safe;

$system_prompt = "You are an SDUI (Server-Driven UI) generator for an Android banking app. 
You must output ONLY valid JSON. Do not include markdown code blocks like ```json, just the raw JSON object.
The root JSON object MUST be a 'Screen' object with the following structure:
{
  \"id\": \"$page_id\",
  \"title\": \"<Generated Title Based On Prompt>\",
  \"content\": { <Widget Object> }
}

A <Widget Object> has a \"type\" (string), optional \"properties\" (object), and optional \"children\" (array of Widget Objects).
Supported Widget Types and properties:
- \"column\", \"lazy_column\", \"row\": {\"padding\": 16}. Takes \"children\".
- \"text\": {\"text\": string, \"style\": \"titleLarge\"|\"titleMedium\"|\"bodyLarge\"|\"bodyMedium\", \"padding\": 16}
- \"balance_card\": {\"balance\": \"₹...\", \"account_number\": string, \"account_type\": string}
- \"progress_item\": {\"label\": string, \"progress\": float (0.0-1.0), \"details\": string}
- \"quick_action\": {\"label\": string}
- \"transaction_item\": {\"title\": string, \"subtitle\": string, \"amount\": number, \"date\": string}
- \"bill_card\": {\"provider\": string, \"amount\": number, \"due_date\": string, \"type\": string, \"card_number\": string}
- \"analytics_chart\": {\"title\": string, \"data\": [numbers]}

Based on the User Prompt, generate a beautiful, appropriate combination of these widgets.

User Prompt: " . $prompt;

$data = [
    "contents" => [
        [
            "parts" => [
                ["text" => $system_prompt]
            ]
        ]
    ]
];

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));

$response = curl_exec($ch);
$httpcode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
curl_close($ch);

if ($httpcode !== 200) {
    http_response_code(500);
    echo json_encode(["error" => "AI Service Error", "details" => $response]);
    exit;
}

$response_data = json_decode($response, true);
$ai_text = $response_data['candidates'][0]['content']['parts'][0]['text'] ?? '';

// Strip Markdown formatting if AI included it anyway
$ai_text = trim($ai_text);
if (strpos($ai_text, '```json') === 0) {
    $ai_text = substr($ai_text, 7);
    if (substr($ai_text, -3) === '```') {
        $ai_text = substr($ai_text, 0, -3);
    }
} else if (strpos($ai_text, '```') === 0) {
    $ai_text = substr($ai_text, 3);
    if (substr($ai_text, -3) === '```') {
        $ai_text = substr($ai_text, 0, -3);
    }
}
$ai_text = trim($ai_text);

// Validate JSON
$decoded = json_decode($ai_text, true);
if (json_last_error() !== JSON_ERROR_NONE) {
    http_response_code(500);
    echo json_encode(["error" => "Invalid JSON from AI", "raw" => $ai_text]);
    exit;
}

// 3. Cache it in Database
try {
    $stmt = $pdo->prepare("INSERT INTO dynamic_pages (page_id, title, ui_json) VALUES (:page_id, :title, :ui_json)");
    $stmt->execute([
        'page_id' => $page_id,
        'title' => $decoded['title'] ?? 'AI Response',
        'ui_json' => $ai_text
    ]);
} catch (PDOException $e) {
    // Ignore duplicate key errors if multiple concurrent requests happen
}

echo $ai_text;
?>
