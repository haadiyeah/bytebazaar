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
        return 0;
    }

    public int makeShipment(String OId, String DeliverTo, String Address, String Phone, String Email) {
        int trackId;
        Order o = null;
        for (Order order : orderList) {
            if (Integer.parseInt(OId) == order.getOrderID()) {
                o = order;
                break;
            }
        }
        trackId = o.createShipment(OId, DeliverTo, Address, Phone, Email);

        return trackId;
    }
}
