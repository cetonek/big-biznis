CREATE TABLE average_salary (
 	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    quarter SMALLINT NOT NULL CHECK (quarter >= 1 AND quarter <= 4),
    year SMALLINT NOT NULL CHECK (year >= 1800 AND year <= 3000),
    salary_crowns INTEGER NOT NULL CHECK (salary_crowns >= 0),
    UNIQUE (quarter, year)
);

INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2000, 11941);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2000, 13227);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2000, 12963);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2000, 14717);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2001, 13052);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2001, 14391);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2001, 14117);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2001, 15908);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2002, 14083);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2002, 15599);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2002, 15268);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2002, 17133);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2003, 14986);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2003, 16529);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2003, 16088);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2003, 18096);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2004, 16231);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2004, 17223);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2004, 17190);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2004, 19183);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2005, 17067);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2005, 18112);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2005, 18203);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2005, 19963);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2006, 18270);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2006, 19300);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2006, 19305);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2006, 21269);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2007, 19687);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2007, 20740);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2007, 20721);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2007, 22641);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2008, 21632);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2008, 22246);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2008, 22181);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2008, 24309);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2009, 22108);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2009, 22796);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2009, 23091);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2009, 25418);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2010, 22738);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2010, 23504);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2010, 23600);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2010, 25591);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2011, 23372);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2011, 24116);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2011, 24107);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2011, 26211);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2012, 24131);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2012, 24627);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2012, 24439);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2012, 27055);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2013, 23985);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2013, 24877);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2013, 24735);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2013, 26525);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2014, 24931);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2014, 25569);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2014, 25279);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2014, 27261);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2015, 25497);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2015, 26408);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2015, 26163);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2015, 28258);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2016, 26683);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2016, 27452);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2016, 27396);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2016, 29491);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2017, 28034);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2017, 29432);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2017, 29234);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2017, 31802);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2018, 30230);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2018, 31815);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2018, 31533);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2018, 33871);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(1, 2019, 32489);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(2, 2019, 34115);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(3, 2019, 33732);
INSERT INTO average_salary (quarter, year, salary_crowns) VALUES(4, 2019, 36144);
