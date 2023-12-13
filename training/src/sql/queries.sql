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

-----------------------------------------------------------------------
--En pahalı ürünün hangisi?
Select * from products WHERE unit_price = (Select MAX(unit_price) from products) -- İç içe sorgular - Sub queries


-- En çok satış yaptığım ürün hangisi ?

-- En karlı satış yaptığım ürün hangisi ?

-- Kullanıcının girdiği kategori ismindeki ürünler ?

--JOINLER => FK ile bağlı iki tablonu tek veri setinde toplanması.
--INNER JOIN
Select * from products INNER JOIN categories on products.category_id = categories.category_id
Select products.unit_price, categories.category_name from products INNER JOIN categories on products.category_id = categories.category_id
-- alias => takma ad
Select p.unit_price, c.category_name from products p INNER JOIN categories c on p.category_id = c.category_id
Select * from products p inner join order_details od on p.product_id = od.product_id

-- Hiç sipariş edilmemiş ürün?
-- products inner join order_details

-- Her ürünün sipariş miktarını bulmak
-- 80 idli ürün 0 kez sipariş edilmiştir.
Select * from products p left join order_details od on p.product_id = od.product_id where p.product_id=80

Select * from products p full outer join order_details od on p.product_id = od.product_id


Select * from products p
                  INNER JOIN categories c on p.category_id = c.category_id
                  INNER JOIN order_details od on p.product_id=od.product_id
                  INNER JOIN orders o on od.order_id = o.order_id
                  INNER JOIN employees e on o.employee_id=e.employee_id
--

-- Limit X => X adet veri getirir.
Select * from products order by unit_price desc LIMIT 10

-- Group By
Select city, count(*) from customers
group by city
-- group by'da groupladığım alanları seçebilirim

-- having => Group BY'ın where'i
Select product_id,quantity, count(*) from order_details
group by product_id,quantity
HAVING product_id=19

-- 50'den fazla en fazla sipariş alan çalışanların ilk 5'i
-- Alias verirken çift tırnak!!
Select e.first_name as "Ad", e.last_name as "Soyad", count(*) as "Toplam Sipariş Sayısı" from orders o
                                                                                                  INNER JOIN employees e on o.employee_id=e.employee_id
group by e.first_name,e.last_name
HAVING count(*) > 50
order by count(*) desc
    LIMIT 5

------------------------------------------------------------
Select * from categories

-- Insert INTO =>
    Insert Into categories(category_name, description, picture) VALUES('Insert deneme', 'Bu sql komutuyla eklenmiştir', NULL)
-- Update, Delete => Sensitive
-- Bir koşul verilmediğinde tüm veri setini etkiler.
UPDATE categories
SET category_name = 'Update deneme', description = 'Bu sql komutuyla güncellenmiştir'
WHERE category_id = 10
DELETE from categories WHERE category_id = 11
-- Hard Delete
DELETE from categories WHERE category_id = 1 -- Silinmez hata verir, Detail: Key (category_id)=(1) is still referenced from table "products".

-- Soft Delete
-- DELETE değil UPDATE kullanılır.
-- Bu yaklaşımda veri silinmez bir alanı setlenir (deleted -> true , delete_date -> 12.03.2022)
Select * from categories WHERE deleted_date = NULL -- Silinmemiş verileri getir. (deleted_date alanı null ise bu veri silinmemiş demektir.)


    S