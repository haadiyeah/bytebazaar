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

    public Order(int orderID, Date orderDate, Time orderTime, int buyerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.buyerID = buyerID;
        productsList=new LinkedList<SalesLineItem>();
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
    public int getTotalItems(){
        return productsList.size();
    }
    public float getTotalBill(){
        float ret=0;
        for(int i=0;i<productsList.size();i++) {
            ret+= productsList.get(i).getPrice() * productsList.get(i).getQuantity();
        }
        return ret;
    }
}
