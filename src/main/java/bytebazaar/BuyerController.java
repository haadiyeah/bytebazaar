package bytebazaar;

import java.util.LinkedList;

public class BuyerController {
    UserLedger userLedger;
    ProductLedger productLedger;
    FAQLedger faqLedger;

    BuyerController() {
        userLedger=new UserLedger();
        productLedger=new ProductLedger();
        faqLedger=new FAQLedger();
        faqLedger.populateFAQs();//Will refresh (repopulate) the faqs
    }

    public int buyNow(LinkedList<SalesLineItem> productsList) {
        int orderID = userLedger.getCurrentUser().buyNow(productsList);
        return orderID;
    }

    public void clearCart() {
        ((Buyer)userLedger.getCurrentUser()).clearCart();
    }

    public float getLatestOrderBill() {
        return ((Buyer)userLedger.getCurrentUser()).getLastOrderBill() ;
    }


    // Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return userLedger.createUser(name, email, phone, password);
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        return productLedger.getProducts(filter, categories);// will automatically set in itself too
        // return productLedger.getProductLedger();//will return the set products
    }

    //Function that makes shipment and returns trackID
    //If an error occurs, it will return -1
    public int shipment(String DeliverTo, String Address, String Phone, String Email) {
        //Buyer currBuyer = (Buyer)getCurrentUser();
        if( getCurrentUser() instanceof Buyer ) {
            int OId = ((Buyer)getCurrentUser()).getOrders().getLastOrder().getOrderID();
            if(OId >0)
                return ((Buyer)getCurrentUser()).shipment(OId, DeliverTo, Address, Phone, Email);
            else
                return -1;
        } else {
            return -1;
        }
    }

    public void cancelLatestOrder() {
        
    }

    public LinkedList<String> getLatestOrderInfo() {
        LinkedList<String> retList= new LinkedList<String>();

        //Returns a list of required info in this order: ID, Name, Address, Phone, Email
        retList.add( ((Buyer)getCurrentUser()).getOrders().getLastOrder().getOrderID() + "" ) ;
        retList.add( ((Buyer)getCurrentUser()).getOrders().getLastOrder().getShip().getDeliverTo() );
        retList.add( ((Buyer)getCurrentUser()).getOrders().getLastOrder().getShip().getAddress() );
        retList.add( ((Buyer)getCurrentUser()).getOrders().getLastOrder().getShip().getPhone() );
        retList.add( ((Buyer)getCurrentUser()).getOrders().getLastOrder().getShip().getEmail() );
        return retList;
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

    public boolean deleteBuyer() {
        return userLedger.deleteBuyer(getCurrentUser());
    }

    //When browsing products, buyer may want to see average rating
    public float getAverageProductRating(int id) {
        return productLedger.getAverageRating( id);
    }

    //When browsing products, buyer may want to get reviews
    public LinkedList<Review> getReviews(int id) {
        return productLedger.getReviews(id);
    }

    public boolean submitReview(String reviewText, int rating, int userID, int productID){
        return ReviewLedger.createNewReview(reviewText, rating, userID, productID);
    }

    //Will refresh (repopulate) the faqs from db and then return
    public LinkedList<FAQ> refreshFAQs() {
        return faqLedger.populateFAQs();
    }

    //Will return the current stored FAQs in the db
    public LinkedList<FAQ> getFAQs() {
        return faqLedger.getStoredFAQs();
    }

    //Function used to search faqs
    public LinkedList<FAQ> findInFaq(String text) {
        return faqLedger.findInFAQs(text);
    }

    public float getCartTotal() {
      return 0; //TODO
    }

    public void updateCartItemQty(int indexNo, char amount) {
      //  userLedger.getCurrentUser().
    }

}
