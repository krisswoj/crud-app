CREATE TABLE IF NOT EXISTS users
(
    id          bigserial       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL,
    surname     VARCHAR(255)    NOT NULL,
    email       VARCHAR(255)    NOT NULL,
    avatar_url  VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL,
    modified_at TIMESTAMP
);
