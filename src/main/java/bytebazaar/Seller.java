package bytebazaar;

import java.util.LinkedList;

public class Seller {
    private String email; //also the primary key in the db
    private String password;
    private String phoneNum;
    private String name;
    private int ID;
    private String storeInformation;
    private LinkedList<Order> ordersRecieved;
    private LinkedList<Product> personalProductsCatalog;

    public Seller(String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        ordersRecieved = new LinkedList<Order>();
        personalProductsCatalog= new LinkedList<Product>();
    }

    public Seller(int id, String email, String password, String phoneNum, String name) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.ID=id;
        ordersRecieved = new LinkedList<Order>();
        personalProductsCatalog= new LinkedList<Product>();
    }

    public String getStoreInformation() {
        return storeInformation;
    }

    public void setStoreInformation(String storeInformation) {
        this.storeInformation = storeInformation;
    }

    public void setDetails() {
        ordersRecieved= DBHandler.getInstance().getOrderLog(this.ID);
        personalProductsCatalog = DBHandler.getInstance().getPersonalProductCatalog(this.ID);

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
