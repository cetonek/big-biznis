CREATE TABLE exchange_rate (
 	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,

    exchange_date DATE NOT NULL CHECK (exchange_date >= '1800-01-01' AND exchange_date <= '3000-01-01'),
    currency_code VARCHAR(255) NOT NULL,
    currency_name VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    exchange_rate NUMERIC NOT NULL,
    country VARCHAR(255) NOT NULL,

    UNIQUE (exchange_date, currency_code)
);
