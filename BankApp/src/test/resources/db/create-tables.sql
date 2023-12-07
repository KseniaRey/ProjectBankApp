CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY,
    role       varchar(20),
    status     varchar(20),
    tax_code    varchar(20),
    password   varchar(60),
    first_name varchar(50),
    last_name  varchar(50),
    email      varchar(60),
    address    varchar(80),
    phone      varchar(20),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS products
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    name          varchar(70),
    status        varchar(20),
    interest_rate decimal(6, 4),
    currency_code varchar(3),
    min_limit     int,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS accounts
(
    id            UUID PRIMARY KEY,
    client_id     UUID,
    name        varchar(28) UNIQUE,
    type  varchar(20),
    status        varchar(20),
    balance       decimal(15, 2),
    currency_code varchar(3),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES users (id)
    );

CREATE TABLE IF NOT EXISTS agreements
(
    id            UUID PRIMARY KEY,
    account_id    UUID,
    product_id    int,
    manager_id    UUID,
    status        varchar(20),
    sum           decimal(15, 2),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (manager_id) REFERENCES users (id)
    );

CREATE TABLE IF NOT EXISTS transactions
(
    id                        UUID PRIMARY KEY,
    debit_account_id          UUID,
    credit_account_id         UUID,
    type                      varchar(20),
    amount                    decimal(12, 4),
    description               varchar(255),
    created_at                TIMESTAMP,
    FOREIGN KEY (debit_account_id) REFERENCES accounts (id),
    FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
    );