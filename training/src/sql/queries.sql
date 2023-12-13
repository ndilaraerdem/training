---SQL => Structured Query Languagae
-- DDL => DML 
-- * => all
Select * from products
-- Select [kolon(lar), *] from [tablo_ismi]
Select product_id, product_name from products
-- Filter with WHERE
SELECT * FROM products WHERE supplier_id > 5
SELECT product_id, product_name, supplier_id FROM products WHERE supplier_id > 5
SELECT * FROM products WHERE supplier_id > 5 and category_id = 1
SELECT * FROM products WHERE supplier_id > 5 or category_id = 1
SELECT * FROM products WHERE (supplier_id > 5 or category_id = 1) and unit_price > 15

-- Order by [column]
Select * from products ORDER BY product_name -- A-Z
Select * from products ORDER BY supplier_id -- küçükten büyüğe
Select * from products ORDER BY product_name DESC -- Z-A
Select * from products ORDER BY supplier_id DESC -- büyükten küçüğe

--Combining Queries
SELECT * from products WHERE units_in_stock > 20 ORDER BY product_name
SELECT * from products ORDER BY product_name WHERE units_in_stock > 20  -- hata
-- önce filtre sonra sıralama yazılır.

--KEYWORDS
--Distinct: farklı , birbirinden ayrı
Select city from customers ORDER BY city -- tekrarlı veriler var
Select DISTINCT city from customers ORDER BY city -- tekrarlı veriler tablodan kaldırıldı
Select DISTINCT city, region from customers ORDER BY city
-- "" => ''
Select * from customers WHERE city = 'México D.F.'
-- % bu metinden öncesi/sonrası önemli değil
Select * from customers WHERE contact_name LIKE '%Mar%'
-- H ile başlayan s ile biten customerlar
Select * from customers WHERE contact_name LIKE 'H%'                                                                                                                             
-- s ile biten customerlar
Select * from customers WHERE contact_name LIKE '%s'
-- Mart ile başlayan customerlar
Select * from customers WHERE contact_name LIKE '%Mart'

-- Built in Function
-- LOWER()
-- UPPER()
-- SUBSTRING()
-- TRIM()

Select LOWER(customer_id) from customers
Select UPPER(company_name) from customers
--MART, mart
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('%mart') --
--li_ -> li ile başlayan 3 karakterli bir data. liu, liz
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('li_') ORDER BY contact_name
--li ile başlayan 6 karakterli bir data (boşlukta bir karakter) 
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('li______') ORDER BY contact_name
-- Elizabeth
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('_li%') ORDER BY contact_name
-- 3. karakteri i olan veriler
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('__i%') ORDER BY contact_name
-- 3. harfi i olup i ile bitmeyen veriler
Select * from customers WHERE LOWER(contact_name) LIKE LOWER('__i_%') ORDER BY contact_name

-- SUM - bir column'ın toplamı
Select SUM(unit_price) from order_details
Select SUM(units_in_stock) from products
-- COUNT - veri adeti
Select COUNT(*) from customers 
-- AVG
Select AVG(unit_price) from products
Select SUM(unit_price) / COUNT(units_in_stock) from products

-- MIN
Select MIN(unit_price) from products
-- MAX
Select MAX(unit_price) from products

-- Select * from products WHERE unit_price = MAX(unit_price)

-- IN
Select * from customers WHERE customer_id IN('ALFKI','ANATR', 'AROUT')
-- BETWEEN
Select * from products WHERE unit_price BETWEEN 10 and 20

-- JOIN
Select * from products 
Select * from suppliers


















