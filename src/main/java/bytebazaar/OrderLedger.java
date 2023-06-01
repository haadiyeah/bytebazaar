package bytebazaar;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OrderLedger {
    private LinkedList<Order> orderList;

    public OrderLedger() {
        orderList = new LinkedList<Order>();
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
        int returningOrderID = DBHandler.getInstance().saveOrder(o);// will return -1 if failed to save
        o.setOrderID(returningOrderID);

        Alert alr=new Alert(AlertType.INFORMATION);
        alr.setHeaderText("returning order id= " + returningOrderID);
        alr.show();

        if (returningOrderID != -1)
            orderList.add(o);
        else
            System.out.println("MADE ORDER AND ORDERID RETURNED NULL, NOT ADDED IN ORDERLIST");

        return returningOrderID;
    }

    public boolean removeOrder(int orderID) {
        for (Order order : orderList) {
            if (order.getOrderID() == orderID) {
                DBHandler.getInstance().deleteOrder(orderID);
                orderList.remove(order);
                System.out.println("Order with order ID " + orderID + " has been removed.");
                return true;
            }
        }
        System.out.println("Order with order ID " + orderID + " not found.");
        return false;
        
    }

    public Order getOrderByOrderID(int orderID) {
        if (!orderList.isEmpty()) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getOrderID() == orderID) {
                    return orderList.get(i);
                }
            }
        }
        return null;
    }

    public void addOrder(Order o) {
        orderList.add(o);
    }

    // public Order getLastOrder() {
    //     if (!orderList.isEmpty()) {
    //         for (int i = 0; i < orderList.size(); i++) {
    //             System.out.println("\n\n\n ORDERRRRRR " + orderList.get(i).getOrderID() + "\n\n");
    //         }
    //         return orderList.getLast();
    //     }
    //     return null; // Return null if the list is empty
    // }

    // public float getLastOrderBill() {
    //     if (!orderList.isEmpty()) {
    //         return orderList.getLast().getTotalBill();
    //     }
    //     return -1; // Return null if the list is empty
    // }

    public int makeShipment(int oId, String DeliverTo, String Address, String Phone, String Email) {
        int trackId;
        Order o = null;
        for (Order order : orderList) {
            if (oId == order.getOrderID()) {
                o = order;
                trackId = o.createShipment(DeliverTo, Address, Phone, Email);
                DBHandler.getInstance().saveShipment(o.getShipment());
                return trackId;
            }
        }
        return -1; // Error

    }
}
