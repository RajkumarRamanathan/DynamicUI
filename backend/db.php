<?php
$host = 'localhost';
$db   = 'missiong1_dynamic_ui';
// TODO: User needs to provide the username and password in this file or via environment variables
$user = 'missiong1_RAANJ';
$pass = 'Raanj@2026';
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

try {
     $pdo = new PDO($dsn, $user, $pass, $options);
     
     // Ensure users table exists
     $pdo->exec("CREATE TABLE IF NOT EXISTS users (
         id INT AUTO_INCREMENT PRIMARY KEY,
         username VARCHAR(255) UNIQUE NOT NULL,
         password VARCHAR(255) NOT NULL,
         role VARCHAR(50) DEFAULT 'user',
         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     )");
} catch (\PDOException $e) {
     throw new \PDOException($e->getMessage(), (int)$e->getCode());
}
?>
