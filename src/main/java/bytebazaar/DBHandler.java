package bytebazaar;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

public class DBHandler {

    private static DBHandler instance;
    private  String connectionURL;

    private Connection myconn = null;
    private  Statement mystmt = null;
    private  String sql = null;
    private  ResultSet myRs = null;

    private  HashMap<String, Integer> mapCategories;

    private DBHandler() {
        mapCategories = new HashMap<String, Integer>();
        mapCategories.put("Keyboards", 1);
        mapCategories.put("Mice", 2);
        mapCategories.put("Monitors", 3);
        mapCategories.put("Graphic cards", 4);
        mapCategories.put("Controllers", 5);
        mapCategories.put("Laptops", 6);
        mapCategories.put("PCs", 7);

        connectionURL = "jdbc:sqlserver://DESKTOP-61OOJ8F\\SQLEXPRESS;" +
                "databaseName=bytebazaar;" +
                "IntegratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;" +
                "MultipleActiveResultSets=True";
        // connectionURL="jdbc:mysql://localhost:3306/mysqljdbc";

    }

    public static DBHandler getInstance() {
        if (instance == null)
            instance = new DBHandler();

        return instance;
    }


    public void save_faq(FAQ faq) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {

            String query = "INSERT INTO FAQS (faqQuestion, faqAnswer) VALUES ('" + faq.getQuestion() + "', '"
                    + faq.getAnswer() + "');";
            System.out.println("========================\n\n\n\n" + query + "\n\n\n\n");
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteOrder(int orderID) {

        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            // Delete rows from orderHasProduct table
            PreparedStatement deleteOrderHasProductStmt = connection
                    .prepareStatement("DELETE FROM orderHasProduct WHERE orderID = " + orderID);
            // deleteOrderHasProductStmt.setInt(1, orderID);
            deleteOrderHasProductStmt.executeUpdate();

            // Delete rows from orders table
            PreparedStatement deleteOrdersStmt = connection
                    .prepareStatement("DELETE FROM orders WHERE orderID = " + orderID);
            // deleteOrdersStmt.setInt(1, orderID);
            deleteOrdersStmt.executeUpdate();

            // Delete rows from Shipment table
            PreparedStatement deleteShipmentStmt = connection
                    .prepareStatement("DELETE FROM Shipment WHERE OrderID = " + orderID);
            // deleteShipmentStmt.setInt(1, orderID);
            deleteShipmentStmt.executeUpdate();

            System.out.println("Yo Man Deleted Rows with order ID " + orderID + " successfully.");
        } catch (SQLException e) {
            System.out.println("Error occurred while dropping rows with order ID " + orderID + ": " + e.getMessage());
        }
    }

    // Will return latest orderID
    public int saveOrder(Order o) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {

            String query = "INSERT INTO orders (orderDate, orderTime, buyerID) VALUES ('" + o.getOrderDate()
                    + "','"
                    + o.getOrderTime() + "'," + o.getBuyerID() + ");";

            System.out.println("\n\n\n!!!!!!!!!!!!! SAVING !!!!!!!!!!!! \n\n\n" + query + "\n\n\n");

            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            // Get the latest orderID, which was just inserted
            ResultSet resultSet = stmt
                    .executeQuery("SELECT MAX(orderID) FROM orders;");

            int orderID;
            if (resultSet.next()) {
                orderID = resultSet.getInt(1);
                saveOrderHasProduct(stmt, orderID, o.getProductsList());
                return orderID;
            } else { // Fail to save
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Will return the newly created product id
    //if failed will return -1
    public int saveProduct(int sellerID, String name, float price, int qty, String imgUrl, String desc, int category) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "INSERT INTO products(productSeller, productName, productPrice, productQuantity, productImageURL, productDescription, productCategory) "
                    +
                    "VALUES (" + sellerID + ",'" + name + "'," + price + "," + qty + ",'" + imgUrl + "','" + desc + "',"
                    + category + ");";
            System.out.println("========================\n\n\n\n" + query + "\n\n\n\n");
            stmt.executeUpdate(query);

            String query2 = "SELECT MAX(productID) FROM  products;";
            ResultSet rs = stmt.executeQuery(query2);
            if (!rs.next()) {
                return -1;
            } else {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
    public void updateOrderPaidStatus(int orderID, boolean paid) {

        try (Connection connection = DriverManager.getConnection(connectionURL);
                PreparedStatement updateStmt = connection
                        .prepareStatement("UPDATE orders SET paid = ? WHERE orderID = ?")) {

            // Set the parameter values
            updateStmt.setBoolean(1, paid);
            updateStmt.setInt(2, orderID);

            // Execute the update statement
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paid status updated for order ID " + orderID);
            } else {
                System.out.println("No order found with order ID " + orderID);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while updating the paid status: " + e.getMessage());
        }
    }

    private void saveOrderHasProduct(Statement stmt, int orderId, LinkedList<SalesLineItem> productsList)
            throws SQLException {
        for (SalesLineItem p : productsList) {
            String query = "INSERT INTO orderHasProduct (orderID, productID, quantity) VALUES (" + orderId + ","
                    + p.getProductID() + "," + p.getQuantity() + ");";
            System.out.println("\n\n\n!!!!!!!!!!!!! SAVING ORDERHASPRODUCT !!!!!!!!!!!! \n\n\n" + query + "\n\n\n");
            stmt.executeUpdate(query);
        }
    }

    public void saveShipment(Shipment shipment) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO Shipment (OrderID, TrackID, DeliverTo, Address, Phone, Email) VALUES (?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1, shipment.getOrderID());
            stmt.setInt(2, shipment.getTrackID());
            stmt.setString(3, shipment.getDeliverTo());
            stmt.setString(4, shipment.getAddress());
            stmt.setString(5, shipment.getPhone());
            stmt.setString(6, shipment.getEmail());

            stmt.executeUpdate();

            System.out.println("Shipment inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int shipmentAPI(int orderID) {
        String insertQuery = "INSERT INTO ShipmentAPI (OrderID) VALUES (?)";
        String selectQuery = "SELECT MAX(TrackID) AS MaxTrackID FROM ShipmentAPI";

        try (Connection connection = DriverManager.getConnection(connectionURL);
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            insertStatement.setInt(1, orderID);
            insertStatement.executeUpdate();

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Retrieve the generated TrackID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if an error occurred or no TrackID is available

    }

    public String fetchAns(String question) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            ResultSet resultSet = stmt
                    .executeQuery("SELECT Faqs.faqAnswer FROM Faqs WHERE faqQuestion='" + question + "';");

            if (!resultSet.next()) {
                return null;
            } else {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<FAQ> getFAQs() {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            LinkedList<FAQ> returnList = new LinkedList<FAQ>();
            ResultSet resultSet = stmt
                    .executeQuery("SELECT Faqs.faqQuestion, Faqs.faqAnswer FROM Faqs;");

            if (!resultSet.next()) {
                return null;
            } else {
                do {
                    returnList.add(new FAQ(resultSet.getString(1), resultSet.getString(2)));
                } while (resultSet.next());

                return returnList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Authenticate login.
    // Returns a BUYER object
    // returns null if an account with the email doesnt exist OR if password doesnot
    // match with the records,
    public Buyer authenticateBuyerLogin(String email, String password) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "SELECT * FROM buyers WHERE userEmail='" + email + "';";
            // if (type.equals("Admin")) {
            // query = "SELECT * FROM users WHERE userEmail='" + email
            // + "' AND userID NOT IN (SELECT buyers.buyerID FROM buyers) AND userID NOT IN
            // (SELECT sellers.sellerID FROM sellers);";
            // } else {
            // query = "SELECT * FROM users WHERE userEmail='" + email + "';";
            // }
            ResultSet resultSet = stmt.executeQuery(query);

            if (!resultSet.next()) {
                return null;
            } else {
                do {
                    if (password.equals(resultSet.getString(3))) {
                        int uID = resultSet.getInt(1);
                        String uEmail = resultSet.getString(2);
                        String uPass = resultSet.getString(3);
                        String uPhone = resultSet.getString(4);
                        String uName = resultSet.getString(5);
                        System.out.println("Returning authenticate now");

                        // if (type.equals("Buyer"))
                        return new Buyer(uID, uEmail, uPass, uPhone, uName);
                        // else if (type.equals("Seller"))
                        // return new Seller(uID, uEmail, uPass, uPhone, uName);
                        // else if (type.equals("Admin"))
                        // return new Admin(uID, uEmail, uPass, uPhone, uName);
                    }
                } while (resultSet.next());

                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Seller getSeller(String email, String password) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
           
            //Checking if user exists.
            String query = "SELECT * FROM buyers WHERE userEmail='" + email + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                return null;
            }

            //If you are here, the person is registered as a buyer, and possibly also as a seller.
            //This function adds them to the sellers if they aren't already added.
            String initQuery ="EXEC registerSellerIfNotRegistered '"+email+"';";
            stmt.executeUpdate(initQuery);

            //Now select the info.
            String query2 = "SELECT sellers.userID, userEmail, userPassword, userPhone, userName FROM sellers JOIN buyers ON buyers.userID=sellers.userID WHERE userEmail='"
                    + email + "' AND userPassword='" + password + "';";
            ResultSet resultSet = stmt.executeQuery(query2);

            if (!resultSet.next()) {
                return null;
            } else {
                int uID = resultSet.getInt(1);
                String uEmail = resultSet.getString(2);
                String uPass = resultSet.getString(3);
                String uPhone = resultSet.getString(4);
                String uName = resultSet.getString(5);
                System.out.println("Returning authenticate now");
                return new Seller(uID, uEmail, uPass, uPhone, uName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Admin authenticateAdminLogin(String email, String password) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "SELECT * FROM admins WHERE userEmail='" + email + "' AND userPassword='" + password + "';";
            ResultSet resultSet = stmt.executeQuery(query);

            if (!resultSet.next()) {
                return null;
            } else {
                int uID = resultSet.getInt(1);
                String uEmail = resultSet.getString(2);
                String uPass = resultSet.getString(3);
                String uPhone = resultSet.getString(4);
                String uName = resultSet.getString(5);
                System.out.println("Returning authenticate now");
                return new Admin(uID, uEmail, uPass, uPhone, uName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUserExists(String email) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            ResultSet resultSet = stmt.executeQuery("SELECT userPassword FROM buyers WHERE userEmail='" + email + "';");

            if (resultSet.next() == false) {
                return false;
            } else {
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // The below function returns the newly assigned buyerID after saving
    // If an error occurs it returns -1
    public int saveBuyer(Buyer b) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "EXEC addBuyer '" + b.getName() + "','" + b.getEmail() + "', '" + b.getPhoneNum() + "', '"
                    + b.getPassword() + "';";
            // System.out.println("========================\n\n\n\n" + query + "\n\n\n\n");
            stmt.executeUpdate(query);

            String query2 = "SELECT dbo.getPersonID('" + b.getEmail() + "');";
            ResultSet resultSet = stmt.executeQuery(query2);

            if (resultSet.next() == false) {
                return -1;// failed to save
            } else {
                return resultSet.getInt(1); // remember in resultset, the first col is 1
            }

        } catch (SQLException e) {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setHeaderText("An error occurred");
            warn.setContentText("Check the terminal");
            warn.showAndWait();
            e.printStackTrace();
            return -2;
        }

    }

    public boolean saveReview(Review r) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "INSERT INTO reviews (reviewRating, reviewText, reviewWriterID, reviewProductID) VALUES("
                    + r.getRating() + ", '" + r.getReviewText() + "'," + r.getPersonID() + ", " + r.getProductID()
                    + ");";
            // System.out.println("========================\n\n\n\n" + query + "\n\n\n\n");
            stmt.executeUpdate(query);
            return true;

        } catch (SQLException e) {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setHeaderText("An error occurred");
            warn.setContentText("Check the terminal");
            warn.showAndWait();
            e.printStackTrace();
            return false;
        }
    }

    // gets a list of orders placed by the buyer whose id is passed
    // Returns null if exception is thrown.
    public LinkedList<Order> getOrderHistory(int userID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting order history");
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM orders WHERE orders.buyerID=" + userID + "  ORDER BY orders.orderDate DESC;");

            if (resultSet.next() == false) {
                System.out.println("returning empty order list");
                return new LinkedList<Order>();// Sending back an empty list as the user had no orders
            } else {
                System.out.println("Compiling orders list");
                LinkedList<Order> returnList = new LinkedList<Order>();
                // Getting all the orders
                do {

                    returnList.add(new Order(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3),
                            resultSet.getInt(4)));

                } while (resultSet.next());

                // For each order, add the list of sale items
                for (int i = 0; i < returnList.size(); i++) {
                    ResultSet resultSet2 = stmt.executeQuery(
                            "SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID) WHERE orderID="
                                    + returnList.get(i).getOrderID() + ";");
                    while (resultSet2.next()) {
                        returnList.get(i).addSaleItemToOrder(new SalesLineItem(resultSet2.getInt(1),
                                resultSet2.getString(2), resultSet2.getFloat(3), resultSet2.getInt(4)));
                    }
                }

                return returnList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // gets a list of orders placed to the seller
    // Returns null if exception is thrown.
    public LinkedList<Order> getOrderLog(int userID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting order log");
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM orders WHERE orderID IN (SELECT orderHasProduct.orderID FROM orderHasProduct WHERE orderHasProduct.productID IN (SELECT products.productID FROM products WHERE productSeller="
                            + userID + "));");

            if (resultSet.next() == false) {
                System.out.println("returning empty order list");
                return new LinkedList<Order>();// Sending back an empty list as the user had no orders
            } else {
                System.out.println("Compiling orders list");
                LinkedList<Order> returnList = new LinkedList<Order>();
                // Getting all the orders
                do {
                    // For each order, add the list of sale items
                    returnList.add(new Order(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3),
                            resultSet.getInt(4)));
                } while (resultSet.next());

                for (int i = 0; i < returnList.size(); i++) {
                    ResultSet rs2 = stmt
                            .executeQuery("SELECT * FROM Shipment WHERE OrderID=" + returnList.get(i).getOrderID());
                    if (rs2.next()) {
                        returnList.get(i).setShipment(new Shipment(rs2.getInt(2), rs2.getString(4), rs2.getString(5),
                                rs2.getString(6), rs2.getString(7)));
                    }
                }

                for (int i = 0; i < returnList.size(); i++) {
                    ResultSet resultSet2 = stmt.executeQuery(
                            "SELECT orderHasProduct.productID, productName, productPrice, quantity FROM orderHasProduct JOIN products ON (products.productID=orderHasProduct.productID AND productSeller="
                                    + userID + ") WHERE orderID="
                                    + returnList.get(i).getOrderID() + ";");

                    System.out.println("Order: " + returnList.get(i).getOrderID() + " Has items: ");
                    while (resultSet2.next()) {
                        System.out.println(resultSet2.getString(2));
                        returnList.get(i).addSaleItemToOrder(new SalesLineItem(resultSet2.getInt(1),
                                resultSet2.getString(2), resultSet2.getFloat(3), resultSet2.getInt(4)));
                    }

                }
                return returnList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // gets a list of all the products sold by the particular seller
    public LinkedList<Product> getPersonalProductCatalog(int userID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting order log");
            ResultSet resultSet = stmt
                    .executeQuery("SELECT* FROM products WHERE products.productSeller=" + userID + ";");

            if (resultSet.next() == false) {
                System.out.println("returning empty product list");
                return new LinkedList<Product>();// Sending back an empty list as the user had no products
            } else {
                System.out.println("Compiling products list");
                LinkedList<Product> returnList = new LinkedList<Product>();
                // Getting all the products
                do {
                    System.out.println("Adding product " + resultSet.getString(2));
                    returnList.add(new Product(resultSet.getInt(1), resultSet.getFloat(3), resultSet.getString(2),
                            resultSet.getInt(7), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
                } while (resultSet.next());

                return returnList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get a list of products acording to the filter and category
    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting order log");

            // Making the query
            String query = "SELECT products.* FROM products LEFT JOIN orderHasProduct ON (products.productID = orderHasProduct.productID) LEFT JOIN orders ON (orderHasProduct.orderID = orders.orderID) ";
            if (categories.size() < 7 && categories.size() > 0) {
                query += "WHERE productCategory=";
                for (int i = 0; i < categories.size(); i++) {
                    query += mapCategories.get(categories.get(i));
                    if (i < categories.size() - 1) {
                        query += " OR productCategory=";
                    }
                }
            }
            query += "GROUP BY products.productID, products.productName, products.productPrice, products.productImageURL, products.productDescription, products.productCategory, products.productSeller, products.productQuantity ";
            if (filter.equals("Top Selling"))
                query += "ORDER BY COUNT(orders.orderID) DESC;";
            else if (filter.equals("Name - A-Z"))
                query += "ORDER BY productName ASC;";
            else if (filter.equals("Name - Z-A"))
                query += "ORDER BY productName DESC;";
            else if (filter.equals("Price - High to Low"))
                query += "ORDER BY productPrice DESC;";
            else if (filter.equals("Price - Low to High"))
                query += "ORDER BY productPrice ASC;";
            else // default is top-selling
                query += "ORDER BY COUNT(orders.orderID) DESC;";

            System.out.println(query);
            ResultSet resultSet = stmt
                    .executeQuery(query);

            if (resultSet.next() == false) {
                System.out.println("returning empty product list");
                return new LinkedList<Product>();// Sending back an empty list as no products to match the reqs exist in db
            } else {

                LinkedList<Product> returnList = new LinkedList<Product>();
                // Getting all the products
                do {
                    System.out.println("Compiling products list, adding prod " + resultSet.getString(2));
                    returnList.add(new Product(resultSet.getInt(1), resultSet.getFloat(3), resultSet.getString(2),
                            resultSet.getInt(7), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
                } while (resultSet.next());

                return returnList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // gets product seller name of a specific id
    public String getProductSeller(int sellerID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting product seller");

            // Making the query
            String query = "SELECT buyers.userName FROM buyers WHERE userID = " + sellerID
                    + " AND userID =SOME (SELECT sellers.userID FROM sellers);";
            ResultSet resultSet = stmt
                    .executeQuery(query);

            if (resultSet.next() == false) {
                System.out.println("no such seller found");
                return "";
            } else {
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateBuyer(int id, String name, String email, String password, String phone, String address) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {
            // Making the query
            String query1 = "UPDATE buyers SET userEmail='" + email + "', userPassword='" + password + "', userPhone='"
                    + phone + "',userName='" + name + "' WHERE userID=" + id + ";";
            String query2 = "UPDATE buyers SET deliveryDetails='" + address + "' WHERE userID=" + id + ";";
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {
            // Making the query
            String query1 = "DELETE FROM buyers WHERE userID=" + id + ";";
            // String query2 = "";
            // if (userType.equals("Buyer"))
            // query2 = "DELETE FROM buyers WHERE buyerID=" + u.getID() + ";";
            // else if (userType.equals("Seller"))
            // query2 = "DELETE FROM sellers WHERE sellerID=" + u.getID() + ";";

            stmt.executeUpdate(query1);
            // stmt.executeUpdate(query2);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public float getAverageRating(int id) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting avg rating");

            ResultSet resultSet = stmt.executeQuery(
                    "SELECT ROUND(AVG(CAST(reviewRating AS FLOAT)), 2) AS avgRating FROM reviews WHERE reviewProductID = "
                            + id + ";");

            if (resultSet.next() == false) {
                System.out.println("no rating found");
                return 0;
            } else {
                return resultSet.getFloat(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public LinkedList<Review> getReviews(int id) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT reviewText, reviewRating, userName, reviewProductID FROM reviews JOIN buyers ON(buyers.userID=reviews.reviewWriterID) WHERE reviewProductID="
                            + id + ";");

            if (resultSet.next() == false) {
                return null;
            } else {

                LinkedList<Review> returnList = new LinkedList<Review>();
                // Getting all the reviews
                do {
                    returnList.add(new Review(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3),
                            resultSet.getInt(4)));
                } while (resultSet.next());

                return returnList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    // public boolean hasBoughtProduct(int buyerID, int productID) {
    //     try (
    //             Connection con = DriverManager.getConnection(connectionURL);
    //             Statement stmt = con.createStatement())

    //     {
    //         String query = "SELECT p.productID FROM products p WHERE p.productID=" + productID + " AND p.productID IN (" +
    //                 "SELECT products.productID FROM products  WHERE products.productID IN (" +
    //                 "SELECT orderHasProduct.productID FROM orderHasProduct WHERE orderHasProduct.orderID IN (" +
    //                 "SELECT orders.orderID from orders WHERE orders.buyerID = " + buyerID + "	)) );";
    //         ResultSet resultSet = stmt.executeQuery(query);
    //         if(resultSet.next()) {
    //             return true;
    //         } else {
    //             return false;
    //         }

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }
}
