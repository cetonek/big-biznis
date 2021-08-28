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

INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'ATS', 'šilink', 1, 2.6, 'Rakousko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'AUD', 'dolar', 1, 21.39, 'Austrálie');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'BEC', 'frank', 100, 88.57, 'Belgie');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'CAD', 'dolar', 1, 23.89, 'Kanada');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'CHF', 'frank', 1, 21.45, 'Švýcarsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'DEM', 'marka', 1, 18.32, 'SRN');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'DKK', 'koruna', 1, 4.74, 'Dánsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'ESP', 'peseta', 100, 28.68, 'Španělsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'FIM', 'marka', 1, 7.57, 'Finsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'FRF', 'frank', 1, 5.38, 'Francie');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'GBP', 'libra', 1, 52.72, 'V. Britanie');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'GRD', 'drachma', 100, 17.48, 'Řecko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'IEP', 'libra', 1, 48.62, 'Irska rep.');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'ITL', 'lira', 1000, 24.3, 'Itálie');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'JPY', 'jen', 100, 20.46, 'Japonsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'LUF', 'frank', 100, 88.57, 'Lucembursko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'NLG', 'gulden', 1, 16.24, 'Nizozemsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'NOK', 'koruna', 1, 4.66, 'Norsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'PTE', 'eskudo', 100, 20.46, 'Portugalsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'SEK', 'koruna', 1, 4.88, 'Švédsko');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'USD', 'dolar', 1, 27.73, 'USA');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'XDR', 'SDR', 1, 39.35, 'ZPC SDR');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'XEU', 'ECU', 1, 37.43, 'EMS');
INSERT INTO exchange_rate (exchange_date, currency_code, currency_name, amount, exchange_rate, country) VALUES('1991-01-01', 'YUD', 'dinar', 1, 2.6, 'SFRJ');
