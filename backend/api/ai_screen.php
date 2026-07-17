<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
require_once '../db.php';

header('Content-Type: application/json');

$prompt = $_GET['prompt'] ?? '';
$refresh = isset($_GET['refresh']) && $_GET['refresh'] === 'true';

if (empty($prompt)) {
    http_response_code(400);
    echo json_encode(["error" => "Prompt is required"]);
    exit;
}

// Create a unique page_id based on the prompt (slugify)
$page_id = "ai_" . md5(strtolower(trim($prompt)));

// 1. Check Cache (skip if refresh=true)
if (!$refresh) {
    $stmt = $pdo->prepare("SELECT ui_json FROM dynamic_pages WHERE page_id = :page_id");
    $stmt->execute(['page_id' => $page_id]);
    $cached_page = $stmt->fetch();

    if ($cached_page) {
        echo $cached_page['ui_json'];
        exit;
    }
}

// 2. Call Gemini API
// Fetch API key from settings table
$stmt = $pdo->prepare("SELECT setting_value FROM settings WHERE setting_key = 'gemini_api_key'");
$stmt->execute();
$row = $stmt->fetch();
$api_key = $row ? $row['setting_value'] : '';

if (empty($api_key)) {
    http_response_code(500);
    echo json_encode(["error" => "API key not configured in settings."]);
    exit;
}

$url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" . $api_key;
$user_name = isset($_GET['user_name']) ? urldecode($_GET['user_name']) : 'Alpha Investor';

$mock_user_data = [
    "user_name" => $user_name,
    "accounts" => [
        ["type" => "Premium Savings Account", "number" => "XXXX-XXXX-9876", "balance" => "₹4,52,890.50"],
        ["type" => "Platinum Credit Card", "number" => "XXXX-XXXX-4409", "balance" => "₹1,20,000.00"]
    ],
    "goals" => [
        ["name" => "New SUV Fund", "target" => 1500000, "current" => 1080000],
        ["name" => "Europe Vacation 2024", "target" => 400000, "current" => 180000]
    ],
    "bills" => [
        ["provider" => "Tata Power Delhi", "amount" => 4850, "due_date" => "24th Oct 2023"],
        ["provider" => "Airtel Fiber Broadband", "amount" => 1199, "due_date" => "28th Oct 2023"]
    ],
    "recent_transactions" => [
        ["name" => "Starbucks Coffee", "category" => "Food & Dining", "amount" => -450, "date" => "Today"],
        ["name" => "Monthly Salary", "category" => "Income", "amount" => 175000, "date" => "01 Oct 2023"],
        ["name" => "Amazon India", "category" => "Shopping", "amount" => -3499, "date" => "Yesterday"]
    ]
];
$data_json = json_encode($mock_user_data, JSON_PRETTY_PRINT);

$system_prompt = "You are an SDUI (Server-Driven UI) generator for an Android banking app. 
You must output ONLY valid JSON. Do not include markdown code blocks like ```json, just the raw JSON object.
The root JSON object MUST be a 'Screen' object with the following structure:
{
  \"id\": \"$page_id-" . time() . "\",
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

CRITICAL INSTRUCTIONS FOR UI GENERATION:
1. Make the UI EXTREMELY rich, vibrant, and highly detailed using emojis.
2. We want to test different UI layouts for the SAME data. You MUST use the exact data provided below for the content of your widgets (balances, names, amounts).
3. Do NOT hallucinate new numbers or transaction names. Use the data provided.
4. DO radically change the layout, the order, the combinations of widgets (e.g. maybe put bills in a row, or transactions in a column, or use different widget types for different data) each time you are called, to show off different design variations.
5. Combine multiple different widgets in a `lazy_column` to create a beautiful, comprehensive dashboard.

=== REAL USER DATA TO DISPLAY ===
$data_json
=================================

Based on the User Prompt and the Real User Data, generate a beautiful, appropriate combination of widgets.

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

// 3. Cache it in Database (Upsert)
try {
    // Check if it exists first
    $stmt = $pdo->prepare("SELECT 1 FROM dynamic_pages WHERE page_id = :page_id");
    $stmt->execute(['page_id' => $page_id]);
    if ($stmt->fetch()) {
        $updateStmt = $pdo->prepare("UPDATE dynamic_pages SET title = :title, ui_json = :ui_json WHERE page_id = :page_id");
        $updateStmt->execute([
            'title' => $decoded['title'] ?? 'AI Response',
            'ui_json' => $ai_text,
            'page_id' => $page_id
        ]);
    } else {
        $insertStmt = $pdo->prepare("INSERT INTO dynamic_pages (page_id, title, ui_json) VALUES (:page_id, :title, :ui_json)");
        $insertStmt->execute([
            'page_id' => $page_id,
            'title' => $decoded['title'] ?? 'AI Response',
            'ui_json' => $ai_text
        ]);
    }
} catch (PDOException $e) {
    // Ignore database errors
}

echo $ai_text;
?>
