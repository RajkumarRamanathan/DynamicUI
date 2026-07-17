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
    
    $userName = isset($_GET['user_name']) ? urldecode($_GET['user_name']) : $user['name'];
    
    $screen = [
        "id" => "home",
        "title" => "Good Morning, " . $userName,
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
                "amount" => $bill['amount'],
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
} else if ($id === 'admin') {
    $screen = [
        "id" => "admin",
        "title" => "AI Form Generator",
        "content" => [
            "type" => "lazy_column",
            "children" => [
                [
                    "type" => "text",
                    "properties" => [
                        "text" => "What kind of form do you want to generate? (e.g. 'new Payee', 'loan application')",
                        "style" => "titleMedium",
                        "padding" => 16
                    ]
                ],
                [
                    "type" => "input_text",
                    "properties" => [
                        "id" => "ai_prompt",
                        "label" => "Form Intent"
                    ]
                ],
                [
                    "type" => "submit_button",
                    "properties" => [
                        "label" => "Generate Form",
                        "form_id" => "generate_ai_form"
                    ],
                    "actions" => [
                        "onClick" => [
                            "type" => "generate_ai_form",
                            "payload" => [
                                "prompt_field" => "ai_prompt"
                            ]
                        ]
                    ]
                ]
            ]
        ]
    ];
} else {
    $stmt = $pdo->prepare("SELECT ui_json FROM dynamic_pages WHERE page_id = :page_id");
    $stmt->execute(['page_id' => $id]);
    $dynamic_page = $stmt->fetch();

    if ($dynamic_page) {
        $ui_json_string = $dynamic_page['ui_json'];
        
        // Attempt to pre-fill data
        $table_name = "data_" . $id;
        try {
            $dataStmt = $pdo->prepare("SELECT * FROM `$table_name` WHERE user_id = 1");
            $dataStmt->execute();
            $dataRow = $dataStmt->fetch(PDO::FETCH_ASSOC);
            
            if ($dataRow) {
                $decoded = json_decode($ui_json_string, true);
                
                // Function to recursively find inputs and inject values
                $injectValues = function(&$node) use (&$injectValues, $dataRow) {
                    if (isset($node['type']) && in_array($node['type'], ['input_text', 'input_checkbox'])) {
                        $inputId = $node['properties']['id'] ?? '';
                        // Find matching column
                        foreach ($dataRow as $col => $val) {
                            $clean_id = preg_replace('/[^a-zA-Z0-9_]/', '', $inputId);
                            if ($col === $clean_id && $val !== null) {
                                $node['properties']['value'] = $val;
                                break;
                            }
                        }
                    }
                    
                    if (isset($node['children']) && is_array($node['children'])) {
                        foreach ($node['children'] as &$child) {
                            $injectValues($child);
                        }
                    }
                    if (isset($node['content']) && is_array($node['content'])) {
                        $injectValues($node['content']);
                    }
                };
                
                $injectValues($decoded);
                $ui_json_string = json_encode($decoded);
            }
        } catch (PDOException $e) {
            // Table might not exist, just ignore
        }

        echo $ui_json_string;
        exit;
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
}

echo json_encode($screen);
?>
