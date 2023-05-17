package bytebazaar;

import java.util.LinkedList;

public class Seller extends User {
    private String storeInformation;
    private LinkedList<Order> ordersRecieved;
    private LinkedList<Product> personalProductsCatalog;

    public Seller(String email, String password, String phoneNum, String name) {
        super(email, password, phoneNum, name);
        ordersRecieved = new LinkedList<Order>();
        personalProductsCatalog= new LinkedList<Product>();
    }

    public Seller(int id, String email, String password, String phoneNum, String name) {
        super(id, email, password, phoneNum, name);
        ordersRecieved = new LinkedList<Order>();
        personalProductsCatalog= new LinkedList<Product>();
    }

    public String getStoreInformation() {
        return storeInformation;
    }

    public void setStoreInformation(String storeInformation) {
        this.storeInformation = storeInformation;
    }

    @Override
    public void setDetails() {
        ordersRecieved= DBHandler.getInstance().getOrderLog(getID());
        personalProductsCatalog = DBHandler.getInstance().getPersonalProductCatalog(getID());

    }

    public void addOrder(Order o) {
        ordersRecieved.add(o);
    }

    public void removeOrder(Order o) {
        ordersRecieved.remove(o);
    }

    public void addProduct(Product p){
        personalProductsCatalog.add(p);
    }

    public void removeProduct(Product p) {
        personalProductsCatalog.remove(p);
    }

}
