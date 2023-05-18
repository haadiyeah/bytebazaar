use bytebazaar

DROP TABLE IF EXISTS orderHasProduct;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS buyers;
DROP TABLE IF EXISTS sellers;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	userID INT PRIMARY KEY IDENTITY(100,1),
	userEmail VARCHAR(100),
	userPassword VARCHAR(15) NOT NULL,
	userPhone VARCHAR(20),
	userName VARCHAR(100)
)

CREATE TABLE buyers (
	buyerID INT PRIMARY KEY FOREIGN KEY REFERENCES users(userID) ON DELETE CASCADE,
	deliveryDetails VARCHAR(200) --can be null if delivery details not saved
)

CREATE TABLE sellers (
	sellerID INT PRIMARY KEY FOREIGN KEY REFERENCES users(userID)  ON DELETE CASCADE,
	shopInformation VARCHAR(200) --can be null if not a store
)

CREATE TABLE orders (
	orderID INT PRIMARY KEY IDENTITY (1,1),
	orderDate DATE,
	orderTime TIME, 
	buyerID INT FOREIGN KEY REFERENCES buyers(buyerID) ON DELETE CASCADE
)
CREATE TABLE categories(
	categoryID INT PRIMARY KEY IDENTITY(1,1),
	categoryName VARCHAR(100)
)
CREATE TABLE products (
	productID INT PRIMARY KEY IDENTITY(1,1), 
	productName VARCHAR(500),
	productPrice FLOAT CHECK(productPrice>100), --serious products only <_<
	productImageURL VARCHAR(500), 
	productDescription VARCHAR(500),
	productCategory INT FOREIGN KEY REFERENCES categories(categoryID) ON DELETE SET NULL,
	productSeller INT FOREIGN KEY REFERENCES sellers(sellerID) ON DELETE CASCADE,
	productQuantity INT CHECK(productQuantity>=0)
)

CREATE TABLE orderHasProduct(
	orderID INT NOT NULL,
	productID INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY(orderID, productID)
)


DROP FUNCTION IF EXISTS getPersonID;
GO
CREATE FUNCTION getPersonID (@email VARCHAR(100))
RETURNS INT
AS
BEGIN
RETURN (SELECT userID from users WHERE userEmail=@email)
END 
GO

--Will throw exception if buyer has already signed up with this email!
DROP PROCEDURE IF EXISTS addBuyer;
GO
CREATE PROCEDURE addBuyer(@name VARCHAR(100), @email VARCHAR(100), @phone VARCHAR(20), @password VARCHAR(15))
AS 
BEGIN
INSERT INTO users(userEmail, userName, userPhone, userPassword) VALUES (@email, @name, @phone, @password);
INSERT INTO buyers(buyerID) VALUES (dbo.getPersonID(@email));
END 
GO

DROP PROCEDURE IF EXISTS addSeller;
GO
CREATE PROCEDURE addSeller(@name VARCHAR(100), @email VARCHAR(100), @phone VARCHAR(20), @password VARCHAR(15))
AS 
BEGIN
INSERT INTO users(userEmail, userName, userPhone, userPassword) VALUES (@email, @name, @phone, @password);
INSERT INTO sellers(sellerID) VALUES (dbo.getPersonID(@email));
END 
GO


EXEC addBuyer 'Haadi','haaadi@pk.com', '0336-5285764', 'iamdumb';
EXEC addSeller 'Mama','mamasajid@pk.com', '0336-19191991', 'iamsmart';
INSERT INTO products(productName, productPrice, productQuantity, productSeller, productDescription) VALUES
					('HP laptop', 399.99, 20,101, 'An amazing laptop' ),
					('HP mouse', 100.99, 2,101, 'Lovely mouse chirrr chirrr' );
INSERT INTO orders (orderDate, orderTime, buyerID) VALUES ('2002-02-02', '11:59:59', 100);
INSERT INTO orderHasProduct(orderID, productID, quantity) VALUES (1,1,1); 

SELECT dbo.getPersonID('haaadi@pk.com');

SELECT* FROM buyers;
SELECT* FROM users;
SELECT* FROM sellers;
SELECT* FROM orders;
SELECT* FROM orderHasProduct;
SELECT* FROM products;

--QUERY to get order history from userID

SELECT* FROM orders WHERE orders.buyerID =101;

--populate list of products in order
--for each orderID in
--Selecting all orders made by a particular person
SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID) WHERE orderID=1;

--Selecting all orders which contain a product of a particular seller
SELECT * FROM orders WHERE orderID IN (SELECT orderHasProduct.orderID FROM orderHasProduct WHERE orderHasProduct.productID IN (SELECT products.productID FROM products WHERE productSeller=101));

SELECT* FROM products WHERE products.productSeller=101;

--Selecting products in a specific order, of a specific seller (To make sales line item)
SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID AND productSeller=1) WHERE orderID=101;