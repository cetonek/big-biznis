CREATE TABLE public_debt (
 	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,

    year SMALLINT NOT NULL CHECK (year >= 1800 AND year <= 3000),
    debt_millions_crowns BIGINT NOT NULL,

    UNIQUE (year)
);

INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1993, 158800);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1994, 157300);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1995, 154400);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1996, 155200);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1997, 173141);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1998, 194676);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(1999, 228356);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2000, 289324);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2001, 345045);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2002, 395898);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2003, 493185);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2004, 592900);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2005, 691176);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2006, 802493);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2007, 892300);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2008, 999810);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2009, 1178240);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2010, 1344060);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2011, 1499374);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2012, 1667633);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2013, 1683338);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2014, 1663663);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2015, 1672977);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2016, 1613374);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2017, 1624716);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2018, 1622004);
INSERT INTO public_debt (year, debt_millions_crowns) VALUES(2019, 1640185);
