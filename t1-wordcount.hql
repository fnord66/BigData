DROP TABLE myinput;
DROP TABLE mywords;

CREATE TABLE myinput (line STRING);

-- Load the text from the local filesystem
LOAD DATA LOCAL INPATH './bank/' overwrite INTO TABLE myinput;

CREATE TABLE mywords AS
SELECT EXPLODE(SPLIT(LCASE(REGEXP_REPLACE(line,'[\\p{Punct},\\p{Cntrl}]','')),' ')) AS word
FROM myinput;