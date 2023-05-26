package bytebazaar;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class OrderLedger {
    private LinkedList<Order> orderList;

    public int makeOrder(LinkedList<SalesLineItem> productsList, int buyerID) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        Order o = new Order(buyerID, Date.valueOf(localDate), Time.valueOf(localTime), buyerID);
        o.Create(productsList, buyerID);

        orderList.add(o);
        DBHandler.getInstance().saveOrder(o);
        return 0;
    }

    public Order getLastOrder() {
        if (!orderList.isEmpty()) {
            return orderList.getLast();
        }
        return null; // Return null if the list is empty
    }

    public int makeShipment(int oId, String DeliverTo, String Address, String Phone, String Email) {
        int trackId;
        Order o = null;
        for (Order order : orderList) {
            if (oId == order.getOrderID()) {
                o = order;
                break;
            }
        }
        trackId = o.createShipment(oId, DeliverTo, Address, Phone, Email);
        DBHandler.getInstance().saveShipment(o.getShip());
        return trackId;
    }
}
