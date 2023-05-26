package bytebazaar;

import java.util.LinkedList;

public class BuyerController {
    UserLedger userLedger;
    ProductLedger productLedger;

    BuyerController() {
        userLedger = new UserLedger();
        productLedger = new ProductLedger();
    }

    public int buyNow(LinkedList<SalesLineItem> productsList) {
        Buyer currBuyer = (Buyer) BusinessControllerFactory.getBuyerControllerInst().getCurrentUser();

        int orderID = currBuyer.buyNow(productsList, currBuyer.getID());

        return orderID;
    }

    // Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return userLedger.createUser(name, email, phone, password);
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        return productLedger.getProducts(filter, categories);// will automatically set in itself too
        // return productLedger.getProductLedger();//will return the set products
    }

    public void shipment(String DeliverTo, String Address, String Phone, String Email) {
        Buyer currBuyer = (Buyer) BusinessControllerFactory.getBuyerControllerInst().getCurrentUser();
        int OId = currBuyer.getOrder().getLastOrder().getOrderID();

        int trackId = currBuyer.shipment(OId, DeliverTo, Address, Phone, Email);
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

    public void setCurrentUser(User u) {
        userLedger.setCurrentUser(u);
    }

    public LinkedList<SalesLineItem> getCartList() {
        return userLedger.getCurrentUser().getCartList();
    }

    public void addToCart(Product p) {
        // userLedger.getCurrentUser().addToCart(p);
        userLedger.addToCurrentUsersCart(p);
    }

    public boolean updateCurrentUser(String name, String email, String password, String phone, String address) {
        return userLedger.updateCurrentUser(name, email, password, phone, address);
    }

    public void logout() {
        userLedger.setCurrentUser(null);
    }

    public boolean deleteBuyer(User u) {
        return userLedger.deleteBuyer(u);
    }

}
