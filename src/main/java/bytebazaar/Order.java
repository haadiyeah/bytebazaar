package bytebazaar;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

import api.paymentAPI;

public class Order {
    private int orderID;
    private Date orderDate;
    private Time orderTime;
    private int buyerID;
    private LinkedList<SalesLineItem> productsList;
    private Shipment ship;
    private boolean paid;

    // When fetching orders from the DB, this constructor is called, as OrderID is
    // already known.
    public Order(int orderID, Date orderDate, Time orderTime, int buyerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        productsList = new LinkedList<SalesLineItem>();
    }

    // When creating a new order in the system. This constructor is used as orderID
    // can only be set
    // after saving in the db
    public Order(Date orderDate, Time orderTime, int buyerID, LinkedList<SalesLineItem> listItems) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        this.productsList = listItems;
        this.orderID = -1;
        this.paid = false;
        // Until set, this will remain -1
        // If orderID!= -1, it means it has been stored in DB
    }

    public int createShipment(String DeliverTo, String Address, String Phone, String Email) {
        ship = new Shipment(this.orderID, DeliverTo, Address, Phone, Email);
        ship.setTrackID(ship.Validate());
        DBHandler.getInstance().saveShipment(this.ship);
        return ship.getTrackID();
    }

    public void addSaleItemToOrder(SalesLineItem s) {
        productsList.add(s);
    }

    public float getTotalBill() {
        float ret = 0;
        for (int i = 0; i < productsList.size(); i++) {
            ret += productsList.get(i).getSubTotal();
        }
        return ret;
    }

    public boolean setPaid(String cardNumber, String nameOnCard, String expDate, String cvv, String amount) {
        boolean check = paymentAPI.verify(cardNumber, nameOnCard, expDate, cvv, amount);
        if (check) {
            this.paid = true;
            DBHandler.getInstance().updateOrderPaidStatus(orderID, paid);
            return true;
        } else {
            this.paid = false;
            return false;
        }
    }

    // Checking if a product exists in the order
    public boolean hasProduct(int ProductID) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getProductID() == ProductID) {
                return true;
            }
        }
        return false;
    }

    // General getters and setters are below
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LinkedList<SalesLineItem> getProductsList() {
        return productsList;
    }

    public Shipment getShipment() {
        return ship;
    }

    public void setShipment(Shipment ship) {
        this.ship = ship;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getTotalItems() {
        return productsList.size();
    }

    public boolean isPaid() {
        return paid;
    }

}
