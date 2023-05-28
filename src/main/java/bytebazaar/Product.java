package bytebazaar;

public class Product {
    private int productID;
    private float price;
    private String name;
    private int sellerID;
    private String imageURL;
    private String description;
    private int categoryID;
   
    //IMPORTANT TO NOTE:
    //If category id is null, it will be zero.
    //There are other nullable fields e.g. ImageURL, etc.
    //Any fields nullable in DB schema, are nullable here too.
    public Product(int productID, float price, String name, int sellerID, String imageURL, String description,
            int categoryID) {
        this.productID = productID;
        this.price = price;
        this.name = name;
        this.sellerID = sellerID;
        this.imageURL = imageURL;
        this.description = description;
        this.categoryID = categoryID;
    }
    public Product() {

    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
}
