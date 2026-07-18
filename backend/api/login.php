<?php
header('Content-Type: application/json');
require_once '../db.php';

// Get JSON POST payload
$input = json_decode(file_get_contents('php://input'), true);

if (!is_array($input) || !isset($input['username']) || !isset($input['password'])) {
    http_response_code(400);
    echo json_encode(["success" => false, "message" => "Invalid payload"]);
    exit;
}

$username = trim($input['username']);
$password = $input['password'];

if (empty($username) || empty($password)) {
    http_response_code(400);
    echo json_encode(["success" => false, "message" => "Username and password are required"]);
    exit;
}

try {
    $stmt = $pdo->prepare("SELECT id, username, password, role FROM users WHERE username = :username");
    $stmt->execute(['username' => $username]);
    $user = $stmt->fetch();

    if ($user) {
        // User exists, verify password
        if ($password === $user['password']) { // Plaintext for POC simplicity
            echo json_encode([
                "success" => true,
                "user_id" => (int)$user['id'],
                "role" => $user['role'],
                "message" => "Login successful"
            ]);
        } else {
            echo json_encode([
                "success" => false,
                "message" => "Invalid password"
            ]);
        }
    } else {
        // User does not exist, create them
        $insertStmt = $pdo->prepare("INSERT INTO users (username, password, role) VALUES (:username, :password, 'user')");
        $insertStmt->execute([
            'username' => $username,
            'password' => $password
        ]);
        
        $newUserId = $pdo->lastInsertId();
        
        echo json_encode([
            "success" => true,
            "user_id" => (int)$newUserId,
            "role" => "user",
            "message" => "User registered successfully"
        ]);
    }
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["success" => false, "message" => "Database error: " . $e->getMessage()]);
}
?>
