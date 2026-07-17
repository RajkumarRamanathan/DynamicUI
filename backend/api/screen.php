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
} else if ($id === 'balance') {
    $stmt = $pdo->query("SELECT * FROM accounts WHERE user_id = 1");
    $accounts = $stmt->fetchAll();
    $acc = count($accounts) > 0 ? $accounts[0] : null;
    
    $screen = [
        "id" => "balance",
        "title" => "Account Balance",
        "content" => [
            "type" => "column",
            "children" => [
                [
                    "type" => "balance_card",
                    "properties" => [
                        "balance" => $acc ? "₹" . number_format($acc['balance'], 2) : "₹0.00",
                        "account_number" => $acc ? $acc['account_number'] : "**** 0000",
                        "account_type" => "Total Balance"
                    ]
                ],
                [
                    "type" => "text",
                    "properties" => [
                        "text" => "Saving Goals",
                        "style" => "titleMedium",
                        "padding" => 16
                    ]
                ],
                [
                    "type" => "progress_item",
                    "properties" => [
                        "label" => "New Car",
                        "progress" => 0.65,
                        "details" => "₹8,50,000 / ₹12,00,000"
                    ]
                ],
                [
                    "type" => "progress_item",
                    "properties" => [
                        "label" => "Europe Trip",
                        "progress" => 0.70,
                        "details" => "₹3,50,000 / ₹5,00,000"
                    ]
                ]
            ]
        ]
    ];
} else if ($id === 'analytics') {
    $screen = [
        "id" => "analytics",
        "title" => "Spending Analytics",
        "content" => [
            "type" => "column",
            "children" => [
                [
                    "type" => "analytics_chart",
                    "properties" => [
                        "title" => "Monthly Spends",
                        "data" => [1200, 2500, 1800, 3200, 2100, 2800]
                    ]
                ],
                [
                    "type" => "text",
                    "properties" => [
                        "text" => "Top Categories",
                        "style" => "titleMedium",
                        "padding" => 16
                    ]
                ],
                [
                    "type" => "progress_item",
                    "properties" => [
                        "label" => "Shopping",
                        "progress" => 0.45,
                        "details" => "₹12,400"
                    ]
                ],
                [
                    "type" => "progress_item",
                    "properties" => [
                        "label" => "Food & Drinks",
                        "progress" => 0.25,
                        "details" => "₹6,200"
                    ]
                ],
                [
                    "type" => "progress_item",
                    "properties" => [
                        "label" => "Transport",
                        "progress" => 0.15,
                        "details" => "₹4,100"
                    ]
                ]
            ]
        ]
    ];
} else if ($id === 'pay_bills') {
    $stmt = $pdo->query("SELECT * FROM bills WHERE user_id = 1");
    $bills = $stmt->fetchAll();
    
    $children = [
        [
            "type" => "text",
            "properties" => [
                "text" => "Pending Bills",
                "style" => "titleMedium",
                "padding" => 16
            ]
        ]
    ];
    
    foreach ($bills as $bill) {
        $children[] = [
            "type" => "bill_card",
            "properties" => [
                "provider" => $bill['provider'],
                "amount" => "₹" . number_format($bill['amount'], 2),
                "due_date" => $bill['due_date'],
                "type" => $bill['type'],
                "card_number" => "**** 5678" // mock detail
            ]
        ];
    }
    
    $screen = [
        "id" => "pay_bills",
        "title" => "Pay Bills",
        "content" => [
            "type" => "lazy_column",
            "children" => $children
        ]
    ];
} else if ($id === 'send_money') {
    $screen = [
        "id" => "send_money",
        "title" => "Send Money",
        "content" => [
            "type" => "lazy_column",
            "children" => [
                [
                    "type" => "text",
                    "properties" => [
                        "text" => "Recent Recipients",
                        "style" => "titleMedium",
                        "padding" => 16
                    ]
                ],
                [
                    "type" => "payee_item",
                    "properties" => [
                        "name" => "Arjun Sharma",
                        "details" => "SBI - 1234",
                        "country" => "Indian"
                    ]
                ],
                [
                    "type" => "payee_item",
                    "properties" => [
                        "name" => "Michael Scott",
                        "details" => "Chase - 9988",
                        "country" => "USA"
                    ]
                ]
            ]
        ]
    ];
} else if ($id === 'scan_qr') {
    $screen = [
        "id" => "scan_qr",
        "title" => "Scan QR Code",
        "content" => [
            "type" => "column",
            "children" => [
                [
                    "type" => "text",
                    "properties" => [
                        "text" => "Align QR code within the frame",
                        "style" => "titleMedium",
                        "padding" => 16
                    ]
                ]
            ]
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
