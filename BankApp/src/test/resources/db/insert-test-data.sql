INSERT INTO users (id, user_role, status, tax_code, password, first_name, last_name, email, address, phone, created_at, updated_at)
values ('c586e826-f384-4c06-b00e-75ff679e732f', 'CUSTOMER', 'NEW', '1234567890', 'password123', 'Amisha', 'Smith', 'alice@example.com', '123 Main St', '555-123-4567', '2023-09-17 12:00:00', '2023-09-17 12:00:00');

INSERT INTO products(id, product_name, status, interest_rate, product_currency_code, product_limit, created_at, updated_at)
values (1, 'Mortgage', 'ACTIVE', 3.5, 'USD', 100000, '2023-09-17 12:00:00', '2023-09-17 12:00:00');

INSERT INTO accounts(id, account_name, account_type, account_status, account_balance, currency_code, created_at, updated_at, client_id)
values ('a16b4e7d-8a29-4327-b79c-4f6b21410d12', 'Savings Account', 'TRAVEL', 'ACTIVE', '5000.00', 'USD', '2023-09-17 12:00:00', '2023-09-17 12:00:00', 'c67bfe33-e779-4d0e-81a3-8e6f1870d601');

INSERT INTO accounts(id, account_name, account_type, account_status, account_balance, currency_code, created_at, updated_at, client_id)
values ('2442b573-307c-4f39-994e-5bb359745cba', 'Savings Account', 'TRAVEL', 'ACTIVE', '5000.00', 'USD', '2023-09-17 12:00:00', '2023-09-17 12:00:00', 'c67bfe33-e779-4d0e-81a3-8e6f1870d601');

INSERT INTO agreements(id, account_id, product_id, manager_id, interest_rate, status, sum, created_at, updated_at)
values ('a16b4e7d-8a29-4327-b79c-4f6b21410d12', '1', 'c67bfe33-e779-4d0e-81a3-8e6f1870d601', '3.5', 'ACTIVE', '100000.00', '2023-09-17 12:00:00', '2023-09-17 12:00:00');

INSERT INTO transactions(id, debit_account_id, credit_account_id, transaction_type, transaction_amount, transaction_description, created_at)
values ('01fd4094-d09a-473d-a389-728abda7d813', 'a16b4e7d-8a29-4327-b79c-4f6b21410d12', 'a16b4e7d-8a29-4327-b79c-4f6b21410d12', 'TRANSFER', 500.0000, 'Transfer from Savings to Checking', '2023-09-17 12:00:00');