DROP TABLE customers;

CREATE TABLE customers
(
	age INT,
	job STRING,
	marital STRING,
	education STRING,
	default STRING,
	balance INT,
	housing STRING,
	loan STRING,
	contact STRING,
	day INT,
	month STRING,
	duration INT,
	campaign INT,
	pdays INT,
	previous INT,
	poutcome STRING,
	termDeposit STRING
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\;';

-- Load the text from the local filesystem
LOAD DATA LOCAL INPATH './data\ files/bank/' overwrite INTO TABLE customers;

SELECT REGEXP_REPLACE(customers.education, '\"', '') AS edu, ROUND(AVG(customers.balance)) AS income FROM customers
GROUP BY (customers.education);