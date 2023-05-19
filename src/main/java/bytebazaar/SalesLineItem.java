package bytebazaar;

public class SalesLineItem {
    private int productID;
    private String productName;
    private float price;
    private int quantity;

    public SalesLineItem(int productID, String name, float price, int quantity) {
        this.productID = productID;
        this.price = price;
        this.productName=name;
        this.quantity = quantity;
    }
    //Constructor to create sales line item from product, assumed qty:1
    public SalesLineItem(Product p) {
        this.productID=p.getProductID();
        this.price=p.getPrice();
        this.productName=p.getName();
        this.quantity=1;
    }
    public SalesLineItem(Product p, int qty) {
        this.productID=p.getProductID();
        this.price=p.getPrice();
        this.productName=p.getName();
        this.quantity=qty;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
