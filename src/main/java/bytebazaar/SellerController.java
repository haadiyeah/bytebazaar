package bytebazaar;

import java.util.LinkedList;

public class SellerController {
    SellerLedger sellerLedger;

    public SellerController() {
        sellerLedger = new SellerLedger();
    }

    //Login function for the seller 
    public int login(String email, String password) {
        return sellerLedger.loginRequest(email, password);
    }

    public LinkedList<LinkedList<String>> getOrderDetails(int sellerID, int orderID) {
        //Initializing the list to return
        LinkedList<LinkedList<String>> returnDetails = new LinkedList<LinkedList<String>>();
        
        //Getting the required order.
        Order requiredOrder= sellerLedger.getSeller(sellerID).getOrderByOrderID(orderID);
        LinkedList<SalesLineItem> items = requiredOrder.getProductsList();
        
        //getting the items- each item will have its own linkedlist, with name,price,qty and total.
        //That will,in turn, be added to the whole linkedlist.
        items.forEach(item -> {
            LinkedList<String> itemInfo = new LinkedList<String>();
            itemInfo.add(item.getProductName());
            itemInfo.add(item.getQuantity()+"");
            itemInfo.add(item.getPrice()+"/-");
            itemInfo.add(item.getSubTotal()+"/-");
            returnDetails.add(itemInfo);
        });
        return returnDetails;
    }

    //Getting delivery details of specific order
    public LinkedList<String> getShipmentDetails(int sellerID, int orderID) {
        //Initializing the list to return
        LinkedList<String> returnDetails = new LinkedList<String>();
        
        //Getting the required order.
        Order requiredOrder= sellerLedger.getSeller(sellerID).getOrderByOrderID(orderID);
        
        //Adding the shipment info
        returnDetails.add(requiredOrder.getShipment().getDeliverTo());
        returnDetails.add(requiredOrder.getShipment().getAddress());
        returnDetails.add(requiredOrder.getShipment().getEmail());
        returnDetails.add(requiredOrder.getShipment().getPhone());
        return returnDetails;
    }


    //Getting orders summary
    public LinkedList<LinkedList<String>> getOrdersData(int sellerID) {
        LinkedList<LinkedList<String>> returnData = new LinkedList<LinkedList<String>>();
        //bismillah
        //Getting all orders of the seller whose id was passed
        LinkedList<Order> recievedData = sellerLedger.getOrdersOf(sellerID);

        //Creating a linkedlist with info about each order
        recievedData.forEach(order -> {
            LinkedList<String> orderData = new LinkedList<String>();
            orderData.add(order.getOrderID()+"");
            if(order.getShipment()!=null)
                orderData.add(order.getShipment().getDeliverTo());
            else 
                orderData.add("Unspecified");
            orderData.add(order.getTotalBill()+"");
            orderData.add(order.getOrderDate().toString());
            returnData.add(orderData);
        });
        return returnData;
    }

}
