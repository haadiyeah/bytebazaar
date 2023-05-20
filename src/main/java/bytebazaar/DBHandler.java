package bytebazaar;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

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
    String connectionURL;

    Connection myconn = null;
    Statement mystmt = null;
    String sql = null;
    ResultSet myRs = null;
    String user = "root";
    String pass = "had15mysqL";

    HashMap<String, Integer> mapCategories;

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

    // public void saveOrder(Order o) {
    // try (
    // Connection con = DriverManager.getConnection(connectionURL);
    // Statement stmt = con.createStatement())

    // {

    // String query = "INSERT INTO orders (orderDate, orderTime, buyerID) VALUES
    // ("+o.getOrderDate()+","+o.getOrderTime()+","+o.getBuyerID() + ")";
    // stmt.executeUpdate(query);
    // //stmt.close();

    // for(SalesLineItem p : o.getProductsList())
    // query = "INSERT INTO orderHasProduct (orderID, productID, quantity) VALUES
    // ("+o.getOrderID()+","+p.getProductID()+ "," +p.getQuantity()+")";
    // PreparedStatement preparedStatement = con.prepareStatement(query);

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // }

    public void saveOrder(Order o) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {

            String query = "INSERT INTO orders (orderDate, orderTime, buyerID) VALUES (" + o.getOrderDate() + ","
                    + o.getOrderTime() + "," + o.getBuyerID() + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            saveOrderHasProduct(stmt, o.getOrderID(), o.getProductsList());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveOrderHasProduct(Statement stmt, int orderId, LinkedList<SalesLineItem> productsList)
            throws SQLException {
        for (SalesLineItem p : productsList) {
            String query = "INSERT INTO orderHasProduct (orderID, productID, quantity) VALUES (" + orderId + ","
                    + p.getProductID() + "," + p.getQuantity() + ")";
            stmt.executeUpdate(query);
        }
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

    public void runSelectQuery(String query) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {

            ResultSet resultSet = stmt.executeQuery(query);
            // Print result sets
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            // Print the column names
            for (int i = 1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnName(i) + "\t\t");
            }
            System.out.println();

            // Print the rows
            while (resultSet.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(resultSet.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticate login.
    // Returns a user object that can be cast as seller or user as per need.
    // returns null if an account with the email doesnt exist OR if password does
    // not match with the records,
    public User authenticateLogin(String email, String password, String type) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            String query = "";
            if (type.equals("Admin")) {
                query = "SELECT * FROM users WHERE userEmail='" + email
                        + "' AND userID NOT IN (SELECT buyers.buyerID FROM buyers) AND userID NOT IN (SELECT sellers.sellerID FROM sellers);";
            } else {
                query = "SELECT * FROM users WHERE userEmail='" + email + "';";
            }
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

                        if (type.equals("Buyer"))
                            return new Buyer(uID, uEmail, uPass, uPhone, uName);
                        else if (type.equals("Seller"))
                            return new Seller(uID, uEmail, uPass, uPhone, uName);
                        else if (type.equals("Admin"))
                            return new Admin(uID, uEmail, uPass, uPhone, uName);
                    }
                } while (resultSet.next());

                return null;

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
            ResultSet resultSet = stmt.executeQuery("SELECT userPassword FROM users WHERE userEmail='" + email + "';");

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
    public int save(Buyer b) {
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

    // gets a list of orders placed by the buyer whose id is passed
    // Returns null if exception is thrown.
    public LinkedList<Order> getOrderHistory(int userID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting order history");
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM orders WHERE orders.buyerID=" + userID + ";");

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
                    System.out.println("order: " + resultSet.getInt(1));
                    returnList.add(new Order(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3),
                            resultSet.getInt(4)));
                    // For each order, add the list of sale items
                } while (resultSet.next());

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
                return new LinkedList<Product>();// Sending back an empty list as no products exist in db
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

    public String getProductSeller(int sellerID) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            System.out.println("getting product seller");

            // Making the query
            String query = "SELECT users.userName FROM users WHERE userID = " + sellerID
                    + " AND userID =SOME (SELECT sellers.sellerID FROM sellers);";
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

    public boolean updateUser(int id, String name, String email, String password, String phone, String address) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {
            // Making the query
            String query1 = "UPDATE users SET userEmail='" + email + "', userPassword='" + password + "', userPhone='"
                    + phone + "',userName='" + name + "' WHERE userID=" + id + ";";
            String query2 = "UPDATE buyers SET deliveryDetails='" + address + "' WHERE buyerID=" + id + ";";
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User u, String userType) {
        try (Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement()) {
            // Making the query
            String query1 = "DELETE FROM users WHERE userID=" + u.getID() + ";";
            String query2 = "";
            if (userType.equals("Buyer"))
                query2 = "DELETE FROM buyers WHERE buyerID=" + u.getID() + ";";
            else if (userType.equals("Seller"))
                query2 = "DELETE FROM sellers WHERE sellerID=" + u.getID() + ";";

            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
