package bytebazaar;

import java.util.LinkedList;

public class Buyer extends User {

    private LinkedList<Order> orderHistory;
    private OrderLedger order;
    private Cart cart;
    private String deliveryDetails;

    // Constructor used to create new buyer when id not generated yet
    public Buyer(String email, String password, String phoneNum, String name) {
        super(email, password, phoneNum, name);
        orderHistory = new LinkedList<Order>();

    }

    // Constructor used to create new buyer when id known (e.g.. fetching from db)
    public Buyer(int id, String email, String password, String phoneNum, String name) {
        super(id, email, password, phoneNum, name);
        orderHistory = new LinkedList<Order>();

    }

    public int buyNow(LinkedList<SalesLineItem> productsList, int buyerID) {
        int orderID = order.makeOrder(productsList, buyerID);

        return orderID;

    }

    @Override
    public void setDetails() {
        orderHistory = DBHandler.getInstance().getOrderHistory(getID());
        this.cart = new Cart();
    }

    public LinkedList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(LinkedList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // Assumed qty:1
    @Override
    public void addToCart(Product prod) {
        cart.add(new SalesLineItem(prod));
    }

    @Override
    // Add with given qty
    public void addToCart(Product prod, int qty) {
        cart.add(new SalesLineItem(prod, qty));
    }

    @Override
    public LinkedList<SalesLineItem> getCartList() {
        return cart.itemsList;
    }

    @Override
    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    @Override
    public void setDeliveryDetails(String s) {
        deliveryDetails = s;
    }

}
