CREATE TABLE budget_balance (
 	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    year SMALLINT NOT NULL CHECK (year >= 1800 AND year <= 3000),
    balance_millions_crowns BIGINT NOT NULL ,
    UNIQUE (year)
);

INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1993, 1081);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1994, 10449);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1995, 7230);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1996, -1562);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1997, -15717);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1998, -29331);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(1999, -29634);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2000, -46061);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2001, -67705);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2002, -45716);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2003, -109053);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2004, -93684);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2005, -56338);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2006, -97580);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2007, -66392);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2008, -20003);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2009, -192394);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2010, -156416);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2011, -142771);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2012, -101000);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2013, -81264);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2014, -77782);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2015, -62804);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2016, 61774);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2017, -6151);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2018, 2944);
INSERT INTO budget_balance (year, balance_millions_crowns) VALUES(2019, -28515);
