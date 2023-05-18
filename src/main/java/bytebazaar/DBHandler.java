package bytebazaar;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DBHandler {

    private static DBHandler instance;
    String connectionURL;

    private DBHandler() {
        connectionURL = "jdbc:sqlserver://DESKTOP-61OOJ8F\\SQLEXPRESS;" +
                "databaseName=bytebazaar;" +
                "IntegratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;" +
                "MultipleActiveResultSets=True";

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
            // System.out.println("========================\n\n\n\n" + query + "\n\n\n\n");
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String fetchAns(String question) {
        try (
            Connection con = DriverManager.getConnection(connectionURL);
            Statement stmt = con.createStatement())

    {
        ResultSet resultSet = stmt.executeQuery("SELECT Faqs.faqAnswer FROM Faqs WHERE faqQuestion='" + question + "';");

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
    // Returns a buyer object
    // returns null if an account with the email doesnt exist OR if password does
    // not match with the records,
    public Buyer authenticateBuyerLogin(String email, String password) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users WHERE userEmail='" + email + "';");

            if (!resultSet.next()) {
                return null;
            } else {
                System.out.println(
                        "password entered is " + password + " and resultset getstring 3 is " + resultSet.getString(3));
                //// resultSet.next();
                // System.out.println(" ======================== \n\n " + resultSet.getString(0)
                //// + "\n==============================\n");
                if (password.equals(resultSet.getString(3))) {
                    int uID = resultSet.getInt(1);
                    String uEmail = resultSet.getString(2);
                    String uPass = resultSet.getString(3);
                    String uPhone = resultSet.getString(4);
                    String uName = resultSet.getString(5);
                    System.out.println("Returning authenticate now");

                    return new Buyer(uID, uEmail, uPass, uPhone, uName);

                } else {
                    return null;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Authenticate login.
    // Returns a seller object
    // returns null if an account with the email doesnt exist OR if password does
    // not match with the records,
    public Seller authenticateSellerLogin(String email, String password) {
        try (
                Connection con = DriverManager.getConnection(connectionURL);
                Statement stmt = con.createStatement())

        {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users WHERE userEmail='" + email + "';");

            if (!resultSet.next()) {
                return null;
            } else {
                System.out.println(
                        "password entered is " + password + " and resultset getstring 3 is " + resultSet.getString(3));
                //// resultSet.next();
                // System.out.println(" ======================== \n\n " + resultSet.getString(0)
                //// + "\n==============================\n");
                if (password.equals(resultSet.getString(3))) {
                    int uID = resultSet.getInt(1);
                    String uEmail = resultSet.getString(2);
                    String uPass = resultSet.getString(3);
                    String uPhone = resultSet.getString(4);
                    String uName = resultSet.getString(5);
                    System.out.println("Returning authenticate now");

                    return new Seller(uID, uEmail, uPass, uPhone, uName);

                } else {
                    return null;
                }

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

}
