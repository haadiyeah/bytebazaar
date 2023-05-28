use bytebazaar

--BYTE BAZAAR NEWWW

DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS Shipment;
DROP TABLE IF EXISTS ShipmentAPI;
DROP TABLE IF EXISTS orderHasProduct;
DROP TABLE IF EXISTS productHasSecondaryImage;
DROP TABLE IF EXISTS buyers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS sellers;
DROP TABLE IF EXISTS Faqs;
DROP TABLE IF EXISTS users;


CREATE TABLE buyers (
	userID INT PRIMARY KEY IDENTITY(1,1),
	userEmail VARCHAR(100),
	userPassword VARCHAR(15) NOT NULL,
	userPhone VARCHAR(20),
	userName VARCHAR(100),
	deliveryDetails VARCHAR(200) --can be null if delivery details not saved
)

CREATE TABLE admins (
	userID INT PRIMARY KEY IDENTITY(1,1),
	userEmail VARCHAR(100),
	userPassword VARCHAR(15) NOT NULL,
	userPhone VARCHAR(20),
	userName VARCHAR(100)
)

CREATE TABLE sellers (
	userID INT PRIMARY KEY FOREIGN KEY REFERENCES buyers(userID)  ON DELETE CASCADE,
	shopInformation VARCHAR(255) --can be null if not a store
)

CREATE TABLE orders (
	orderID INT PRIMARY KEY IDENTITY (1,1),
	orderDate DATE,
	orderTime TIME, 
	buyerID INT FOREIGN KEY REFERENCES buyers(userID) ON DELETE CASCADE
	-- order status ??? 
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
	productSeller INT FOREIGN KEY REFERENCES sellers(userID) ON DELETE CASCADE,
	productQuantity INT CHECK(productQuantity>=0)
)

CREATE TABLE orderHasProduct(
	orderID INT NOT NULL FOREIGN KEY REFERENCES orders(orderID) ON DELETE CASCADE,
	productID INT NOT NULL FOREIGN KEY REFERENCES products(productID),
	quantity INT NOT NULL,
	PRIMARY KEY(orderID, productID)
)

CREATE TABLE Faqs (
	faqQuestion VARCHAR(300) PRIMARY KEY,
	faqAnswer VARCHAR(500)
)

CREATE TABLE productHasSecondaryImage(
	productID INT FOREIGN KEY REFERENCES products(productID),
	secondaryImgURL VARCHAR(500) NOT NULL,
	PRIMARY KEY(productID, secondaryImgURL)
)

CREATE TABLE reviews(
	reviewID INT PRIMARY KEY IDENTITY,
	reviewText VARCHAR(500),
	reviewRating INT,
	reviewWriterID INT FOREIGN KEY REFERENCES buyers(userID) ,
	reviewProductID INT FOREIGN KEY REFERENCES products(productID) ON DELETE CASCADE
)

CREATE TABLE Shipment (
  ShipmentID INT PRIMARY KEY IDENTITY(1,1),
  OrderID INT,
  TrackID INT,
  DeliverTo VARCHAR(255),
  Address VARCHAR(255),
  Phone VARCHAR(255),
  Email VARCHAR(255),
  FOREIGN KEY (OrderID) REFERENCES Orders(orderID)
);

Create Table ShipmentAPI(
	TrackID int PRIMARY KEY IDENTITY(1,1),
	OrderID int,
	FOREIGN KEY (OrderID) REFERENCES Orders(orderID)
)

DROP FUNCTION IF EXISTS getPersonID;

--Function to get personID by email, fetching from buyer table
GO
CREATE FUNCTION getPersonID (@email VARCHAR(100))
RETURNS INT
AS
BEGIN
RETURN (SELECT userID from buyers WHERE userEmail=@email)
END 
GO

--Will throw exception if buyer has already signed up with this email!
DROP PROCEDURE IF EXISTS addBuyer;

GO
CREATE PROCEDURE addBuyer(@name VARCHAR(100), @email VARCHAR(100), @phone VARCHAR(20), @password VARCHAR(15))
AS 
BEGIN
INSERT INTO buyers(userEmail, userName, userPhone, userPassword) VALUES (@email, @name, @phone, @password);
END 
GO

--The person would already exist as a buyer if he signed up
DROP PROCEDURE IF EXISTS addSeller;
GO
CREATE PROCEDURE addSeller(@email VARCHAR(100))
AS 
BEGIN
INSERT INTO sellers(userID) VALUES (dbo.getPersonID(@email));
END 
GO


EXEC addBuyer 'Haadi','haaadi@pk.com', '0336-5285764', 'iamdumb';
EXEC addBuyer 'Mama','mamasajid@pk.com', '0336-19191991', 'iamsmart';
EXEC addBuyer 'Najam Uncle','najam@pk.com', '0300-1122334', 'iamfunny';

EXEC addSeller 'mamasajid@pk.com';
EXEC addSeller 'Najam Uncle';

EXEC addBuyer 'Hanaa','hanaasajid@pk.com', '0011223344', 'iamshakka';
EXEC addBuyer 'Shanze','shanijani@pk.com', '0514949149', 'iamsarcasm';
EXEC addBuyer 'Aymun','yumna@pk.com', '1010101010', 'iamintrovert';

INSERT INTO admins(userEmail,  userPassword) VALUES ('admin1@bytebazaar.com', 'iamadmin1');
INSERT INTO admins(userEmail,  userPassword) VALUES ('admin2@bytebazaar.com', 'iamadmin2');

INSERT INTO categories(categoryName) VALUES ('Keyboards'),('Mice'),('Monitors'),('Graphic cards'), ('Controllers'), ('Laptops'),('PCs');

INSERT INTO products(productName,productDescription,productPrice,productQuantity,productSeller,productCategory,productImageURL)
VALUES ('Laptop Vaio TouchScreen', 'Amazing laptop,sleek and smooth,100GB ram', 199999, 23, 3, 6, 'https://icon-library.com/images/icon-laptops/icon-laptops-6.jpg' ),
 ('Dell Laptop', 'Amazing laptop,sleek and smooth,100GB ram', 199999, 23, 3, 6, 'https://icon-library.com/images/icon-laptops/icon-laptops-6.jpg' ),
 ('HP mouse', 'Super fast mouse', 199, 24, 3, 2, 'https://icon-library.com/images/pc-mouse-icon/pc-mouse-icon-6.jpg' ),
 ('HP2 mouse', 'Super fast mouse', 399, 3, 2, 2, 'https://icon-library.com/images/pc-mouse-icon/pc-mouse-icon-6.jpg' ),
 ('HP3 mouse', 'Super fast mouse', 499, 63, 2, 2, 'https://icon-library.com/images/pc-mouse-icon/pc-mouse-icon-6.jpg' ),
 ('Viper monitor', 'Heavy duty monitor for serious teachers', 199999, 23, 3, 3, 'https://icon-library.com/images/computer-monitor-icon/computer-monitor-icon-5.jpg' ),
 ('HP monitor', 'Heavy duty monitor for serious teachers', 39999, 43, 2, 3, 'https://icon-library.com/images/computer-monitor-icon/computer-monitor-icon-5.jpg' ),
 ('Dell monitor', 'Heavy duty monitor for serious teachers', 3099, 20, 2, 3, 'https://icon-library.com/images/computer-monitor-icon/computer-monitor-icon-5.jpg' ),
 ('Xbox Controller', 'Color: white', 1000, 33, 2, 3, 'https://www.pngitem.com/pimgs/m/115-1159532_xbox-one-controller-transparent-background-transparent-background-xbox.png' ),
 ('Nvidia Gforce card', 'Gaming card', 10999, 33, 3, 4, null ),
 ('Gayming keyboard', 'Colorful rainbow lights for gaymers', 5000, 43, 3, 1, 'https://icon-library.com/images/2018/4256670_graphics-card-gigabyte-gtx-1080-ti-hd-png.png' );

insert into faqs(faqQuestion, faqAnswer) VALUES
('Where can I get a refund?','Please contact the respective seller of the product in order to get a refund. We are not responsible in case of damaged or faulty product'),
('How many days does it take to deliver?','Please understand that it takes between 4-14 days to deliver depending on seller. We are not responsible in case of damaged or faulty product'),
('How to get a replacement?','Please contact the respective seller of the product in order to get a replacement. We are not responsible in case of damaged or faulty product'),
('Where did my cart go?','Your cart is temporary and will be deleted if you log out. If you did not log out, but still lose your cart, please email help@bytebazaar.com'),
('Why is product image not showing?','Some seller may have uploaded faulty or incorrect link to their product image. Kindly contact the respective seller in case of such an issue.'),
('How can I use the wishlist?','The wishlist feature is currently under development, so it is not usable. Thank you for your patience.'),
('Why is loading the homepage so slow?','We are still working to make it efficient. Loading images makes it slow. Thank you for your patience.');

select* from products
INSERT INTO orders (orderDate, orderTime, buyerID) VALUES ('2002-02-02', '11:59:59', 1);
INSERT INTO orders (orderDate, orderTime, buyerID) VALUES ('2003-01-02', '05:30:12', 1);

INSERT INTO orderHasProduct(orderID, productID, quantity) VALUES (1,1,1); 
INSERT INTO orderHasProduct(orderID, productID, quantity) VALUES (1,2,1); 
INSERT INTO orderHasProduct(orderID, productID, quantity) VALUES (2,2,1); 
INSERT INTO orderHasProduct(orderID, productID, quantity) VALUES (2,4,1); 

SELECT dbo.getPersonID('haaadi@pk.com');

INSERT INTO reviews (reviewRating, reviewText, reviewWriterID, reviewProductID) VALUES
					(5, 'Amazing product, highly recomment',1, 1),
					(4, 'I bought it for my birtday and i loved it yay',3, 2),
					(3, 'would not buy again i am not satisfied but it works fine',4, 2),
					(2, 'Kept breaking and making peepoopeepoo sounds!!! I do not like this product at all!!! Dont buy from this seller! >:(',6, 3),
					(4, 'Normal,Would not buy again',3, 3),
					(5, 'Amazing product,loved it',2, 1),
					(5, 'Amazing product, highly recommended',5, 1);


SELECT* FROM buyers;
SELECT* FROM products;
SELECT* FROM sellers;
SELECT* FROM orders;
SELECT* FROM orderHasProduct;
SELECT* FROM Shipment;
SELECT* FROM Faqs;
SELECT * FROM reviews;

SELECT MAX(orderID) FROM orders;

SELECT AVG(CAST(reviewRating AS FLOAT)) AS averageRating
FROM reviews
WHERE reviewProductID = 1;

--Finding the average rating for a product
SELECT ROUND(AVG(CAST(reviewRating AS FLOAT)), 2) AS avgRating FROM reviews WHERE reviewProductID = 1;

---query to select the name of te seller whose id is given
--SELECT users.userName FROM users WHERE userID = 2 AND userID =SOME (SELECT sellers.sellerID FROM sellers);


--populate list of products in order
--for each orderID in
--Selecting all orders made by a particular person
SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID) WHERE orderID=1;

--Selecting all orders which contain a product of a particular seller
SELECT * FROM orders WHERE orderID IN (SELECT orderHasProduct.orderID FROM orderHasProduct WHERE orderHasProduct.productID IN (SELECT products.productID FROM products WHERE productSeller=101));

SELECT reviewText, reviewRating, userName, reviewProductID FROM reviews JOIN buyers ON(buyers.userID=reviews.reviewWriterID) WHERE reviewProductID=1;

SELECT products.*
FROM products
LEFT JOIN orderHasProduct ON products.productID = orderHasProduct.productID
LEFT JOIN orders ON orderHasProduct.orderID = orders.orderID
--WHERE productCategory=1 OR productCategory=2
GROUP BY products.productID, products.productName, products.productPrice, products.productImageURL, products.productDescription, products.productCategory, products.productSeller, products.productQuantity
--ORDER BY COUNT(orders.orderID) DESC; --top
--ORDER BY productPrice DESC; --high to low
ORDER BY productName ASC; --A-Z

--Selecting products in a specific order, of a specific seller (To make sales line item)
SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID AND productSeller=1) WHERE orderID=1;


--Getting admin

SELECT * FROM buyers WHERE userEmail='' AND userID NOT IN (SELECT buyers.userID FROM buyers) AND userID NOT IN (SELECT sellers.userID FROM sellers)