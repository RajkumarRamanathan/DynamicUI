CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_premium BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    balance DECIMAL(15, 2),
    account_number VARCHAR(50),
    account_type VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    merchant VARCHAR(255),
    category VARCHAR(100),
    amount VARCHAR(50),
    date_str VARCHAR(50),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);

CREATE TABLE IF NOT EXISTS bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    provider VARCHAR(255),
    amount VARCHAR(50),
    due_date VARCHAR(100),
    card_or_phone_number VARCHAR(100) NULL,
    type VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS payees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(255),
    details VARCHAR(255),
    country VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Mock Data for testing
INSERT INTO users (id, name, is_premium) VALUES (1, 'Rajkumar', TRUE) ON DUPLICATE KEY UPDATE name='Rajkumar';
INSERT INTO accounts (id, user_id, balance, account_number, account_type) VALUES (1, 1, 124500.00, '**** 4589', 'Savings Account') ON DUPLICATE KEY UPDATE balance=124500.00;
INSERT INTO accounts (id, user_id, balance, account_number, account_type) VALUES (2, 1, 500000.00, '**** 1122', 'Premium Wealth Account') ON DUPLICATE KEY UPDATE balance=500000.00;

INSERT INTO transactions (account_id, merchant, category, amount, date_str) VALUES (1, 'Amazon India', 'Shopping', '- ₹2,499.00', '20 Oct');
INSERT INTO transactions (account_id, merchant, category, amount, date_str) VALUES (1, 'HDFC Bank ATM', 'Withdrawal', '- ₹5,000.00', '19 Oct');
INSERT INTO transactions (account_id, merchant, category, amount, date_str) VALUES (1, 'Salary Credit', 'Income', '+ ₹85,000.00', '01 Oct');

INSERT INTO bills (user_id, provider, amount, due_date, card_or_phone_number, type) VALUES (1, 'ICICI Bank Credit Card', '₹52,400.00', 'Due in 2 days', '**** 5678', 'credit_card');
INSERT INTO bills (user_id, provider, amount, due_date, card_or_phone_number, type) VALUES (1, 'Jio Postpaid', '₹799.00', 'Due in 5 days', '+91 91234 56789', 'phone_bill');

INSERT INTO payees (user_id, name, details, country) VALUES (1, 'Arjun Sharma', 'SBI - 1234', 'Indian');
INSERT INTO payees (user_id, name, details, country) VALUES (1, 'Michael Scott', 'Chase - 9988', 'USA');

CREATE TABLE IF NOT EXISTS dynamic_pages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    page_id VARCHAR(50) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    ui_json TEXT NOT NULL
);
