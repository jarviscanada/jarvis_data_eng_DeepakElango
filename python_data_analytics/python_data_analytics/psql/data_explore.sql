-- Practice questions on the retail sql database

-- Question 0
-- Show table schema
\d+ retail;

-- Question 1
-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Question 2
-- Check # of records
SELECT COUNT(*) FROM retail;

-- Question 3
-- number of clients (e.g. unique client ID)
SELECT COUNT(DISTINCT(customer_id)) FROM retail;

-- Question 4
-- invoice date range
SELECT
  MAX(invoice_date),
  MIN(invoice_date)
FROM retail;

-- Question 5
-- number of SKU/merchants
SELECT COUNT(DISTINCT(stock_code)) FROM retail;

-- Question 6
-- Calculate average invoice amount excluding invoices with a negative amount
SELECT
  AVG( invoiceAmount )
FROM (
  SELECT SUM( quantity * unit_price ) AS invoiceAmount FROM retail
  GROUP BY invoice_no
  HAVING SUM( quantity * unit_price ) > 0
  );

-- Question 7
-- Calculate total revenue
SELECT SUM( quantity * unit_price ) FROM retail;

-- Question 8
-- Calculate total revenue by YYYYMM
SELECT
  CAST(EXTRACT(year FROM invoice_date) * 100 + EXTRACT(month FROM invoice_date) AS INTEGER) AS YYYMM,
  SUM( quantity * unit_price )
FROM retail
GROUP BY YYYMM
ORDER BY YYYMM;