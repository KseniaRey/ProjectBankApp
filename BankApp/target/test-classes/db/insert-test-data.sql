INSERT INTO users (id, role, status, tax_code, password, first_name, last_name, email, address, phone, created_at,
                   updated_at)
values ('c67bfe33-e779-4d0e-81a3-8e6f1870d601', 'CUSTOMER', 'NEW', '1234567890', '$2a$04$f/kRo6Sp9uO4L8wxeaw1He.sgbdltPbjQNfaQYB5ZJl/9VzpZgSf2', 'Amisha', 'Smith',
        'alice@example.com', '123 Main St', '555-123-4567', '2023-09-17 12:00:00', '2023-09-17 12:00:00'),
       ('5507c281-154a-4dd1-ba8e-6b32ff4676d0', 'MANAGER', 'NEW', '1234567890', '$2a$04$f/kRo6Sp9uO4L8wxeaw1He.sgbdltPbjQNfaQYB5ZJl/9VzpZgSf2', 'John', 'Jackson',
        'john@example.com', '163 Main St', '555-123-4567', '2023-09-17 12:00:00', '2023-09-17 12:00:00'),
       ('4ede8454-d2ab-4143-a0e2-4f196ec25dbd', 'MANAGER', 'NEW', '1234567890', '$2a$04$f/kRo6Sp9uO4L8wxeaw1He.sgbdltPbjQNfaQYB5ZJl/9VzpZgSf2', 'Alexandra', 'Summer',
        'alex@example.com', '733 Main St', '555-123-4567', '2023-09-17 12:00:00', '2023-09-17 12:00:00');

INSERT INTO products(id, name, status, interest_rate, currency_code, min_limit, created_at, updated_at)
values (1, 'Mortgage', 'ACTIVE', 3.5, 'USD', 100000, '2023-09-17 12:00:00', '2023-09-17 12:00:00');

INSERT INTO accounts(id, name, type, status, balance, currency_code, created_at, updated_at, client_id)
values ('a16b4e7d-8a29-4327-b79c-4f6b21410d12', 'Debit Account', 'TRAVEL', 'ACTIVE', '5000.00', 'USD',
        '2023-09-17 12:00:00', '2023-09-17 12:00:00', 'c67bfe33-e779-4d0e-81a3-8e6f1870d601'),
       ('2442b573-307c-4f39-994e-5bb359745cba', 'Savings Account', 'TRAVEL', 'ACTIVE', '5000.00', 'USD',
        '2023-09-17 12:00:00', '2023-09-17 12:00:00', 'c67bfe33-e779-4d0e-81a3-8e6f1870d601');

INSERT INTO agreements(id, account_id, product_id, manager_id, status, sum, created_at, updated_at)
values ('6cf469b4-a094-47c2-bfd2-3c3bf4742436', 'a16b4e7d-8a29-4327-b79c-4f6b21410d12', '1',
        '5507c281-154a-4dd1-ba8e-6b32ff4676d0', 'ACTIVE', '100000.00', '2023-09-17 12:00:00',
        '2023-09-17 12:00:00');

INSERT INTO transactions(id, debit_account_id, credit_account_id, type, amount, description, created_at)
values ('01fd4094-d09a-473d-a389-728abda7d813', 'a16b4e7d-8a29-4327-b79c-4f6b21410d12',
        'a16b4e7d-8a29-4327-b79c-4f6b21410d12', 'TRANSFER', 500.0000, 'Transfer from Savings to Checking',
        '2023-09-17 12:00:00');