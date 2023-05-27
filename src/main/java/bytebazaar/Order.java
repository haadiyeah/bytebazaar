package bytebazaar;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

public class Order {
    private int orderID;
    private Date orderDate;
    private Time orderTime;
    private int buyerID;
    private LinkedList<SalesLineItem> productsList;
    private Shipment ship;
    private float totalBill;

   

    //When fetching orders from the DB, this constructor is called, as OrderID is already known.
    public Order(int orderID, Date orderDate, Time orderTime, int buyerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        productsList = new LinkedList<SalesLineItem>();
    }

    //When creating a new order in the system. This constructor is used as orderID can only be set 
    //after saving in the db
    public Order(Date orderDate, Time orderTime, int buyerID, LinkedList<SalesLineItem> listItems) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        productsList = listItems;
        this.orderID= -1; 
        //Until set, this will remain -1
        //If orderID!= -1, it means it has been stored in DB
    }

    public void setProductsList(LinkedList<SalesLineItem> productsList) {
        this.productsList = productsList;
    }

    public Shipment getShip() {
        return ship;
    }

    public void setShip(Shipment ship) {
        this.ship = ship;
    }


    public int createShipment(String DeliverTo, String Address, String Phone, String Email) {
        ship = new Shipment(this.orderID, DeliverTo, Address, Phone, Email);
        // s.setAddress(Address);
        int trackId = ship.Validate();
        //ship.setTrackID(trackId);
        return trackId;
    }

    public void addSaleItemToOrder(SalesLineItem s) {
        productsList.add(s);
    }

    public void removeSaleItemFromProduct(SalesLineItem s) {
        productsList.remove(s);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public float getTotalBill() {
        float ret = 0;
        for (int i = 0; i < productsList.size(); i++) {
            ret += productsList.get(i).getSubTotal();
        }
        return ret;
    }

    public LinkedList<SalesLineItem> getProductsList() {
        return productsList;
    }
}
