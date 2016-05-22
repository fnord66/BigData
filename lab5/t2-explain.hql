EXPLAIN
SELECT word, count(1) AS count 
FROM (SELECT EXPLODE(SPLIT(LCASE(REGEXP_REPLACE(line,'[\\p{Punct},\\p{Cntrl}]','')),' ')) AS word
FROM myinput) splitwords
WHERE word NOT LIKE ""
GROUP BY word
ORDER BY count DESC, word ASC
LIMIT 10;