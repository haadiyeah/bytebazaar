package bytebazaar;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class OrderLedger {
    private LinkedList<Order> orderList;

    public OrderLedger() {
        orderList=new LinkedList<Order>();
    }

    public LinkedList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(LinkedList<Order> orderList) {
        this.orderList = orderList;
    }

    public int makeOrder(LinkedList<SalesLineItem> productsList, int buyerID) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        Order o = new Order(Date.valueOf(localDate), Time.valueOf(localTime), buyerID, productsList);
        int returningOrderID= DBHandler.getInstance().saveOrder(o);//will return -1 if failed to save
        o.setOrderID(returningOrderID);
        if(returningOrderID!=-1)
            orderList.add(o);
        else 
            System.out.println("MADE ORDER AND ORDERID RETURNED NULL, NOT ADDED IN ORDERLIST");

        return returningOrderID; 
    }

    public Order getLastOrder() {
        if (!orderList.isEmpty()) {
            for(int i=0;i<orderList.size();i++) {
                System.out.println("\n\n\n ORDERRRRRR " +  orderList.get(i).getOrderID() + "\n\n");
            }
            return orderList.getLast();
        }
        return null; // Return null if the list is empty
    }

    public float getLastOrderBill() {
        if (!orderList.isEmpty()) {
            return orderList.getLast().getTotalBill();
        }
        return -1; // Return null if the list is empty
    }

    public int makeShipment(int oId, String DeliverTo, String Address, String Phone, String Email) {
        int trackId;
        Order o = null;
        for (Order order : orderList) {
            if (oId == order.getOrderID()) {
                o = order;
                trackId = o.createShipment(DeliverTo, Address, Phone, Email);
                DBHandler.getInstance().saveShipment(o.getShip());
                return trackId;
            }
        }
        return -1; //Error
        
    }
}
