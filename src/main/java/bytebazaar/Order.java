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

    public void setProductsList(LinkedList<SalesLineItem> productsList) {
        this.productsList = productsList;
    }

    public Shipment getShip() {
        return ship;
    }

    public void setShip(Shipment ship) {
        this.ship = ship;
    }

    public Order(int orderID, Date orderDate, Time orderTime, int buyerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        productsList = new LinkedList<SalesLineItem>();

    }

    public int createShipment(String OId, String DeliverTo, String Address, String Phone, String Email) {
        ship = new Shipment(buyerID, 0, DeliverTo, Address, Phone, Email);
        // s.setAddress(Address);
        int trackId = ship.Validate(ship);

        return trackId;
    }

    public void Create(LinkedList<SalesLineItem> productsList, int buyerID) {
        this.productsList = productsList;
        this.buyerID = buyerID;
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
            ret += productsList.get(i).getPrice() * productsList.get(i).getQuantity();
        }
        return ret;
    }

    public LinkedList<SalesLineItem> getProductsList() {
        return productsList;
    }
}
