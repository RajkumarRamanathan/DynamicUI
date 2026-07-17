<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
require_once '../db.php';

header('Content-Type: application/json');

$id = $_GET['id'] ?? 'home';

function createQuickAction($label, $action) {
    return [
        "type" => "quick_action",
        "properties" => ["label" => $label],
        "actions" => [
            "onClick" => [
                "type" => "navigate",
                "payload" => ["destination" => $action]
            ]
        ]
    ];
}

function createTransaction($title, $subtitle, $amount, $date) {
    return [
        "type" => "transaction_item",
        "properties" => [
            "title" => $title,
            "subtitle" => $subtitle,
            "amount" => $amount,
            "date" => $date
        ]
    ];
}

$screen = [];

if ($id === 'home') {
    // Fetch data from DB for user 1
    $stmt = $pdo->query("SELECT * FROM users WHERE id = 1");
    $user = $stmt->fetch();
    
    $stmt = $pdo->query("SELECT * FROM accounts WHERE user_id = 1");
    $accounts = $stmt->fetchAll();
    
    $stmt = $pdo->query("SELECT * FROM transactions WHERE account_id = 1 LIMIT 3");
    $transactions = $stmt->fetchAll();

    $children = [];
    
    if (count($accounts) > 0) {
        $acc = $accounts[0];
        $children[] = [
            "type" => "balance_card",
            "properties" => [
                "balance" => "₹" . number_format($acc['balance'], 2),
                "account_number" => $acc['account_number'],
                "account_type" => $acc['account_type']
            ],
            "actions" => [
                "onClick" => [
                    "type" => "navigate",
                    "payload" => ["destination" => "balance_details"]
                ]
            ]
        ];
    }
    
    $children[] = [
        "type" => "row",
        "properties" => ["padding" => 16],
        "children" => [
            createQuickAction("Send", "send_money"),
            createQuickAction("Pay", "pay_bills"),
            createQuickAction("Scan", "scan_qr"),
            createQuickAction("Analytics", "analytics"),
            createQuickAction("History", "transaction_history")
        ]
    ];
    
    $children[] = [
        "type" => "text",
        "properties" => [
            "text" => "Recent Transactions",
            "style" => "titleLarge",
            "padding" => 16
        ]
    ];
    
    if (count($accounts) > 1 && $user['is_premium']) {
        $acc = $accounts[1];
        $children[] = [
            "type" => "balance_card",
            "visibility" => "is_premium==true",
            "animation" => "expand",
            "properties" => [
                "balance" => "₹" . number_format($acc['balance'], 2),
                "account_number" => $acc['account_number'],
                "account_type" => $acc['account_type']
            ]
        ];
    }
    
    foreach ($transactions as $txn) {
        $children[] = createTransaction($txn['merchant'], $txn['category'], $txn['amount'], $txn['date_str']);
    }
    
    $screen = [
        "id" => "home",
        "title" => "Good Morning, " . $user['name'],
        "content" => [
            "type" => "lazy_column",
            "children" => $children
        ]
    ];
} else if ($id === 'statement') {
    $stmt = $pdo->query("SELECT * FROM transactions WHERE account_id = 1");
    $transactions = $stmt->fetchAll();
    $children = [];
    foreach ($transactions as $txn) {
        $children[] = createTransaction($txn['merchant'], $txn['category'], $txn['amount'], $txn['date_str']);
    }
    $screen = [
        "id" => "statement",
        "title" => "E-Statement",
        "content" => [
            "type" => "lazy_column",
            "children" => $children
        ]
    ];
} else {
    // Fallback or generic error screen
    $screen = [
        "id" => "error",
        "title" => "Error",
        "content" => [
            "type" => "text",
            "properties" => [
                "text" => "Screen not found.",
                "style" => "titleLarge",
                "padding" => 16
            ]
        ]
    ];
}

echo json_encode($screen);
?>
