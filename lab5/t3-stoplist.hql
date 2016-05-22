DROP TABLE myinput;
DROP TABLE mywords;
DROP TABLE stopwords;
DROP TABLE stopjoin;
DROP TABLE stoplistout;

CREATE TABLE myinput (line STRING);
CREATE TABLE stopwords (line STRING);

-- Load the text from the local filesystem
LOAD DATA LOCAL INPATH './Sample Files/1/'
	INTO TABLE myinput;

LOAD DATA LOCAL INPATH './Sample Files/3/stoplist.txt'
	INTO TABLE stopwords;

-- Table containing all the words in the myinput table
-- The difference between this table and myinput is that myinput stores each line as a separate row
-- whereas mywords stores each word as a separate row.
CREATE TABLE mywords AS
SELECT EXPLODE(SPLIT(LCASE(REGEXP_REPLACE(line,'[\\p{Punct},\\p{Cntrl}]','')),' ')) AS word
FROM myinput;

CREATE TABLE stopjoin AS
SELECT mywords.word AS mword, stopwords.line AS sword
FROM mywords LEFT OUTER JOIN stopwords
ON (mywords.word = stopwords.line)
WHERE (mywords.word NOT LIKE "");

CREATE TABLE stoplistout AS
SELECT mword FROM stopjoin
WHERE sword is NULL;

-- Dump the output to file
INSERT OVERWRITE LOCAL DIRECTORY './task3-out/'
ROW FORMAT DELIMITED
	FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE
	SELECT * FROM mywords;
