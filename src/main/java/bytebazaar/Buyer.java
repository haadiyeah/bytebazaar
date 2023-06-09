package bytebazaar;

import java.util.LinkedList;

public class Buyer {
    private String email;
    private String password;
    private String phoneNum;
    private String name;
    private int ID;
    private OrderLedger orders;
    private Cart cart;
    private String deliveryDetails;

    // Constructor used to create new buyer when id not generated yet
    public Buyer(String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        orders = new OrderLedger();
    }

    // Constructor used to create new buyer when id known (e.g.. fetching from db)
    public Buyer(int id, String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.ID = id;
        // orderHistory = new LinkedList<Order>();
        orders = new OrderLedger();

    }

    public OrderLedger getOrders() {
        return orders;
    }

    // Buying products; creates an order in orderLedger
    public int buyNow(LinkedList<SalesLineItem> itemsList) {
        int orderID = orders.makeOrder(itemsList, this.getID());
        return orderID;
    }

    // Buying one product
    public int buyNow(Product p) {
        this.cart.clearCart();
        this.cart.addToCart(p);
        int orderID = orders.makeOrder(cart.getItemsList(), this.getID());
        return orderID;
    }

    public void clearCart() {
        cart.clearCart();
    }

    public boolean cancelOrder(int orderID) {
        return orders.removeOrder(orderID);
    }

    public boolean payForOrder(int orderID, String cardNumber, String nameOnCard, String expDate, String cvv,
            String amount) {
        return orders.getOrderByOrderID(orderID).setPaid(cardNumber, nameOnCard, expDate, cvv, amount);
    }

    public float getOrderTotal(int orderID) {
        return orders.getOrderByOrderID(orderID).getTotalBill();
    }

    public int saveShipmentDetails(int oId, String DeliverTo, String Address, String Phone, String Email) {
        int trackId = orders.makeShipment(oId, DeliverTo, Address, Phone, Email);
        return trackId;
    }

    

    public void setDetails() {
        // orderHistory = DBHandler.getInstance().getOrderHistory(getID());
        orders.setOrderList(DBHandler.getInstance().getOrderHistory(getID()));
        this.cart = new Cart();
        System.out.println("SETTING DETAISL");
    }

    public LinkedList<Order> getOrderHistory() {
        return orders.getOrderList();
    }

    public boolean updateCartQuantity(int productID, char updateType) {
        return this.cart.updateItemQty(productID, updateType);
    }

    // Assumed qty:1
    public void addToCart(Product prod) {
        cart.addToCart(prod);
    }

    // // Add with given qty
    // public void addToCart(Product prod, int qty) {
    // cart.add(new SalesLineItem(prod, qty));
    // }

    public LinkedList<SalesLineItem> getCartList() {
        return cart.getItemsList();
    }

    public boolean hasOrderedProduct(int productID) {
        return orders.containsProduct(productID);
        // return DBHandler.getInstance().hasBoughtProduct(this.ID,productID );
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(String s) {
        deliveryDetails = s;
    }

    // General setters and getters..

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

}
