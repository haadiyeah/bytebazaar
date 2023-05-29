package bytebazaar;

import java.util.LinkedList;

public class Seller {
    private String email; //also the primary key in the db
    private String password;
    private String phoneNum;
    private String name;
    private int ID;
    private String storeInformation;
    private OrderLedger ordersRecieved;
    private ProductLedger personalProductsCatalog;

    public Seller(String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        ordersRecieved= new OrderLedger();
        personalProductsCatalog= new ProductLedger();
    }

    public Seller(int id, String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.ID=id;
        ordersRecieved= new OrderLedger();
        personalProductsCatalog= new ProductLedger();
    }

    public String getStoreInformation() {
        return storeInformation;
    }

    public void setStoreInformation(String storeInformation) {
        this.storeInformation = storeInformation;
    }

    public void setDetails() {
        ordersRecieved.setOrderList( DBHandler.getInstance().getOrderLog(this.ID) );
        personalProductsCatalog.setProductLedger(DBHandler.getInstance().getPersonalProductCatalog(this.ID));

    }

    public void addOrder(Order o) {
        ordersRecieved.addOrder(o);
    }

    public LinkedList<Order> getOrders() {
        return ordersRecieved.getOrderList();
    }

    public Order getOrderByOrderID(int id){
        return ordersRecieved.getOrderByOrderID(id);
    }

    // public void removeOrder(Order o) {
    //     ordersRecieved.removeOrder(o);
    // }

    public void addProduct(Product p){
        personalProductsCatalog.addProduct(p);
    }

    // public void removeProduct(Product p) {
    //     personalProductsCatalog.remove(p);
    // }

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
