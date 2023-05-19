package bytebazaar;

import java.util.LinkedList;

public class BuyerController {
    UserLedger userLedger;
    ProductLedger productLedger;

    BuyerController() {
        userLedger=new UserLedger();
        productLedger=new ProductLedger();
    }

    //Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return userLedger.createUser(name, email, phone, password);
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        return productLedger.getProducts(filter, categories);//will automatically set in itself too
        //return productLedger.getProductLedger();//will return the set products
    }
    public void setCurrentProduct(Product p) {
        productLedger.setCurrentProduct(p);
    }
    public Product getCurrentProduct() {
        return productLedger.getCurrentProduct();
    }
    public String getCurrentProductSeller() {
        return productLedger.getProductSeller(productLedger.getCurrentProduct());
    }

    public User getCurrentUser() {
        return userLedger.getCurrentUser();
    }
    public void  setCurrentUser(User u) {
        userLedger.setCurrentUser(u);
    }

    public LinkedList<SalesLineItem> getCartList() {
        return userLedger.getCurrentUser().getCartList();
    }

    public void addToCart(Product p) {
       // userLedger.getCurrentUser().addToCart(p);
       userLedger.addToCurrentUsersCart(p);
    }
}
