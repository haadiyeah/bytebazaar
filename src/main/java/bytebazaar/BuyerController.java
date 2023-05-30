package bytebazaar;

import java.util.LinkedList;

public class BuyerController {
    BuyerLedger buyerLedger;
    ProductLedger productLedger;
    FAQLedger faqLedger;
    ReviewLedger reviewLedger;

    //Constructor, this will only be called once as there will only be 1 instance of buyer (using the businesscontrollerhandler)
    public BuyerController() {
        buyerLedger = new BuyerLedger();
        productLedger = new ProductLedger();
        faqLedger = new FAQLedger();
        reviewLedger = new ReviewLedger();
        faqLedger.populateFAQs();// Will refresh (repopulate) the faqs
    }

    // ---------------------------------Functions related to Account/Session Management ------------------------------------
    // Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return buyerLedger.createBuyer(name, email, phone, password);
    }

    // Login request of buyer is forwarded to buyerLedger.
    public int loginRequest(String email, String password) {
        return buyerLedger.loginRequest(email, password);
    }

    //If user makes edit to their profile on viewing products page.
    public boolean updateBuyer(int buyerID, String name, String email, String password, String phone, String address) {
        return buyerLedger.updateBuyer(buyerID, name, email, password, phone, address);
    }

    //To show buyer info on "Edit profile" and "view profile" page.
    public LinkedList<String> getBuyerInfo(int buyerID) {
        LinkedList<String> retList = new LinkedList<String>();
        Buyer b = buyerLedger.getBuyerByID(buyerID);
        retList.add(b.getName());
        retList.add(b.getEmail());
        retList.add(b.getPhoneNum());
        retList.add(b.getPassword());
        retList.add(b.getDeliveryDetails());
        return retList;
    }

    //To show order summary on viewing profile page
    public LinkedList<Order> getOrderHistory(int buyerID) {
        return buyerLedger.getBuyerByID(buyerID).getOrderHistory();
    }

    public void logout() {
    }

    public boolean deleteBuyer(int buyerID) {
        return buyerLedger.deleteBuyer(buyerID);
    }

    // ------------------------------Functions related to browsing products----------------------------------

    public LinkedList<Product> searchProduct(String text) {
        return productLedger.search(text);
    }

    //When browse products button is clicked
    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        // this will return the productlist inside the ledger, aswell as set the current
        // product ledger
        // according to the filter and categories.
        return productLedger.getProducts(filter, categories);
    }

    // When browsing products, buyer may want to see average rating
    public float getAverageProductRating(int id) {
        return productLedger.getAverageRating(id);
    }

    //When viewing product detail, this function will be used
    public LinkedList<String> getProductInformation(int productID) {
        Product p = productLedger.getProductByProductID(productID);
        LinkedList<String> returnList = new LinkedList<String>();
        if (p != null) {
            returnList.add(p.getName());
            returnList.add(p.getDescription());
            returnList.add(p.getImageURL());
            returnList.add("Rs. " + p.getPrice() + "/-");
            returnList.add(productLedger.getProductSeller(p));
            returnList.add("Average: " + productLedger.getAverageRating(productID) + "/5");
        }
        return returnList;
    }

      // When browsing products, buyer may want to get reviews
      public LinkedList<Review> getReviews(int id) {
        return productLedger.getReviews(id);
    }

    public boolean submitReview(String reviewText, int rating, int userID, int productID) {
        return reviewLedger.createNewReview(reviewText, rating, userID, productID);
    }

    // ----------------------------------Functions related to placing order---------------------------------
    public int buyNow(int buyerID) {
        int orderID = buyerLedger.getBuyerByID(buyerID).buyNow(getCartList(buyerID));
        return orderID;
    }

    public int buyNow(int buyerID, int productID, int quantity) {
        // LinkedList<SalesLineItem> s = new LinkedList<SalesLineItem>();
        Product p = productLedger.getProductByProductID(productID);
        // SalesLineItem s1 = new SalesLineItem(p, quantity);
        // s.add(s1);
        // int orderID = buyerLedger.getBuyerByID(buyerID).buyNow(s);
        int orderID = buyerLedger.getBuyerByID(buyerID).buyNow(p);
        return orderID;
    }

    public boolean cancelOrder(int buyerID, int orderID) {
        return buyerLedger.getBuyerByID(buyerID).cancelOrder(orderID);
    }

    public void paymentConfirmed(int buyerID, int orderID) {
        buyerLedger.getBuyerByID(buyerID).payForOrder(orderID);
    }

    // Function that makes shipment and returns trackID
    // If an error occurs, it will return -1
    public int shipment(int buyerID, int orderID,
            String DeliverTo, String Address, String Phone, String Email) {
        return buyerLedger.getBuyerByID(buyerID).shipment(orderID, DeliverTo, Address, Phone, Email);
    }

    public LinkedList<String> getOrderDeliveryDetails(int buyerID, int orderID) {
        Order o = buyerLedger.getBuyerByID(buyerID).getOrders().getOrderByOrderID(orderID);
        LinkedList<String> retList = new LinkedList<String>();
        if (o != null) {
            // Returns a list of required info in this order: ID, Name, Address, Phone,
            // Email
            retList.add(o.getOrderID() + "");
            retList.add(o.getShip().getDeliverTo());
            retList.add(o.getShip().getAddress());
            retList.add(o.getShip().getPhone());
            retList.add(o.getShip().getEmail());
        }
        return retList;
    }

    
    public LinkedList<String> getOrderSummary(int buyerID, int orderID) {
        LinkedList<String> result = new LinkedList<String>();
        float returnedTotal = buyerLedger.getBuyerByID(buyerID).getOrderTotal(orderID);
        float delivRate = BusinessControllerFactory.getAdminControllerInst().getDeliveryRate();
        result.add("Rs. " + returnedTotal + "/-");
        result.add("Rs. " + delivRate + "/-");
        result.add("Rs. " + (delivRate + returnedTotal) + "/-");
        return result;
    }

    // -------------------------Functions related to cart management--------------------------------------------
    public LinkedList<SalesLineItem> getCartList(int buyerID) {
        return buyerLedger.getCartList(buyerID);
    }

    public void addToCart(int buyerID, int productID) {
        buyerLedger.addToCart(buyerID, productLedger.getProductByProductID(productID));
    }

    // function returns true if the product is still in the cart after updating
    // and returns false if the product has been removed, i.e. quantity is now 0.
    public boolean updateCartItemQty(int buyerID, int productID, char updateType) {
        return buyerLedger.getBuyerByID(buyerID).updateCartQuantity(productID, updateType);
    }

    //-----------------------Functions related to FAQS----------------------------------------------------
    // Will refresh (repopulate) the faqs from db and then return
    public LinkedList<FAQ> refreshFAQs() {
        return faqLedger.populateFAQs();
    }

    // Will return the current stored FAQs in the db
    public LinkedList<FAQ> getFAQs() {
        return faqLedger.getStoredFAQs();
    }

    // Function used to search faqs
    public LinkedList<FAQ> findInFaq(String text) {
        return faqLedger.findInFAQs(text);
    }



    // public Buyer getCurrentUser() {
    // return buyerLedger.getCurrentBuyer();
    // }

    // public void setCurrentUser(Buyer u) {
    // // buyerLedger.setCurrentUser(u);
    // }


}
