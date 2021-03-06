CREATE TABLE gross_domestic_product (
 	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,

    year SMALLINT NOT NULL CHECK (year >= 1800 AND year <= 3000),
    quarter SMALLINT NOT NULL CHECK (quarter >= 1 AND quarter <= 4),
    gdp_type VARCHAR(50) NOT NULL CHECK (gdp_type IN ('NOMINAL', 'REAL_2010_PRICES')),
    gdp_millions_crowns BIGINT NOT NULL,

    UNIQUE (year, quarter, gdp_type)
);

INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1996, 'REAL_2010_PRICES', 687666, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1996, 'REAL_2010_PRICES', 690358, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1996, 'REAL_2010_PRICES', 693643, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1996, 'REAL_2010_PRICES', 693775, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1997, 'REAL_2010_PRICES', 692727, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1997, 'REAL_2010_PRICES', 688614, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1997, 'REAL_2010_PRICES', 684344, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1997, 'REAL_2010_PRICES', 682682, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1998, 'REAL_2010_PRICES', 684147, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1998, 'REAL_2010_PRICES', 684967, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1998, 'REAL_2010_PRICES', 685031, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1998, 'REAL_2010_PRICES', 685446, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1999, 'REAL_2010_PRICES', 684896, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1999, 'REAL_2010_PRICES', 689648, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1999, 'REAL_2010_PRICES', 696592, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(1999, 'REAL_2010_PRICES', 703587, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2000, 'REAL_2010_PRICES', 712279, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2000, 'REAL_2010_PRICES', 722230, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2000, 'REAL_2010_PRICES', 731774, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2000, 'REAL_2010_PRICES', 735948, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2001, 'REAL_2010_PRICES', 741859, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2001, 'REAL_2010_PRICES', 745034, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2001, 'REAL_2010_PRICES', 747928, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2001, 'REAL_2010_PRICES', 751480, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2002, 'REAL_2010_PRICES', 752348, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2002, 'REAL_2010_PRICES', 754393, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2002, 'REAL_2010_PRICES', 760453, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2002, 'REAL_2010_PRICES', 765972, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2003, 'REAL_2010_PRICES', 774461, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2003, 'REAL_2010_PRICES', 780569, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2003, 'REAL_2010_PRICES', 789421, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2003, 'REAL_2010_PRICES', 797847, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2004, 'REAL_2010_PRICES', 806776, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2004, 'REAL_2010_PRICES', 814096, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2004, 'REAL_2010_PRICES', 827717, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2004, 'REAL_2010_PRICES', 843323, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2005, 'REAL_2010_PRICES', 857444, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2005, 'REAL_2010_PRICES', 870898, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2005, 'REAL_2010_PRICES', 883551, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2005, 'REAL_2010_PRICES', 897443, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2006, 'REAL_2010_PRICES', 917673, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2006, 'REAL_2010_PRICES', 934099, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2006, 'REAL_2010_PRICES', 945623, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2006, 'REAL_2010_PRICES', 958716, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2007, 'REAL_2010_PRICES', 974759, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2007, 'REAL_2010_PRICES', 982926, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2007, 'REAL_2010_PRICES', 997754, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2007, 'REAL_2010_PRICES', 1010561, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2008, 'REAL_2010_PRICES', 1013018, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2008, 'REAL_2010_PRICES', 1021356, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2008, 'REAL_2010_PRICES', 1024190, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2008, 'REAL_2010_PRICES', 1007003, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2009, 'REAL_2010_PRICES', 971886, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2009, 'REAL_2010_PRICES', 964196, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2009, 'REAL_2010_PRICES', 968905, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2009, 'REAL_2010_PRICES', 971040, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2010, 'REAL_2010_PRICES', 978297, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2010, 'REAL_2010_PRICES', 988090, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2010, 'REAL_2010_PRICES', 993366, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2010, 'REAL_2010_PRICES', 998564, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2011, 'REAL_2010_PRICES', 1005980, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2011, 'REAL_2010_PRICES', 1007889, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2011, 'REAL_2010_PRICES', 1006485, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2011, 'REAL_2010_PRICES', 1008422, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2012, 'REAL_2010_PRICES', 1007150, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2012, 'REAL_2010_PRICES', 1001934, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2012, 'REAL_2010_PRICES', 996744, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2012, 'REAL_2010_PRICES', 993731, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2013, 'REAL_2010_PRICES', 989215, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2013, 'REAL_2010_PRICES', 990698, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2013, 'REAL_2010_PRICES', 993713, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2013, 'REAL_2010_PRICES', 1006531, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2014, 'REAL_2010_PRICES', 1005161, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2014, 'REAL_2010_PRICES', 1014512, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2014, 'REAL_2010_PRICES', 1027525, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2014, 'REAL_2010_PRICES', 1041020, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2015, 'REAL_2010_PRICES', 1057722, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2015, 'REAL_2010_PRICES', 1073387, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2015, 'REAL_2010_PRICES', 1085201, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2015, 'REAL_2010_PRICES', 1092051, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2016, 'REAL_2010_PRICES', 1095163, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2016, 'REAL_2010_PRICES', 1099020, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2016, 'REAL_2010_PRICES', 1104144, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2016, 'REAL_2010_PRICES', 1111609, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2017, 'REAL_2010_PRICES', 1127268, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2017, 'REAL_2010_PRICES', 1154813, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2017, 'REAL_2010_PRICES', 1160503, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2017, 'REAL_2010_PRICES', 1167883, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2018, 'REAL_2010_PRICES', 1173637, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2018, 'REAL_2010_PRICES', 1180549, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2018, 'REAL_2010_PRICES', 1187996, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2018, 'REAL_2010_PRICES', 1198597, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2019, 'REAL_2010_PRICES', 1206288, 1);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2019, 'REAL_2010_PRICES', 1212254, 2);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2019, 'REAL_2010_PRICES', 1217440, 3);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2019, 'REAL_2010_PRICES', 1223099, 4);
INSERT INTO gross_domestic_product (year, gdp_type, gdp_millions_crowns, quarter) VALUES(2020, 'REAL_2010_PRICES', 1182352, 1);

