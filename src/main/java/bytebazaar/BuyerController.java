package bytebazaar;

import java.util.LinkedList;

public class BuyerController {
    BuyerLedger buyerLedger;
    ProductLedger productLedger;
    FAQLedger faqLedger;

    BuyerController() {
        buyerLedger = new BuyerLedger();
        productLedger = new ProductLedger();
        faqLedger = new FAQLedger();
        faqLedger.populateFAQs();// Will refresh (repopulate) the faqs
    }

    public int buyNow(int buyerID) {
        int orderID = buyerLedger.getBuyerByID(buyerID).buyNow(getCartList(buyerID));
        return orderID;
    }

    public int buyNow(int buyerID, int productID, int quantity) {
        LinkedList<SalesLineItem> s = new LinkedList<SalesLineItem>();
        Product p = productLedger.getProductByProductID(productID);
        SalesLineItem s1 = new SalesLineItem(p, quantity);
        s.add(s1);
        int orderID = buyerLedger.getBuyerByID(buyerID).buyNow(getCartList(buyerID));
        return orderID;
    }

    public boolean cancelOrder(int buyerID, int orderID) {
        return buyerLedger.getBuyerByID(buyerID).cancelOrder(orderID);
    }

    public int loginRequest(String email, String password) {
        return buyerLedger.loginRequest(email, password);
    }

    public LinkedList<Product> searchProduct(String text) {
        LinkedList<Product> resultsToDisplay = new LinkedList<Product>();
        productLedger.getProductLedger().forEach(product -> {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                resultsToDisplay.add(product);
            }
        });
        return resultsToDisplay;
    }

    public LinkedList<String> getOrderSummary(int buyerID, int orderID) {
        LinkedList<String> result = new LinkedList<String>();
        float returnedTotal = buyerLedger.getBuyerByID(buyerID).getOrderTotal(orderID);
        System.out.println("\n\n\n order first prod: " + buyerLedger.getBuyerByID(buyerID).getOrders()
                .getOrderByOrderID(orderID).getProductsList().getFirst().getProductName());
        float delivRate = BusinessControllerFactory.getAdminControllerInst().getDeliveryRate();
        result.add("Rs. " + returnedTotal + "/-");
        result.add("Rs. " + delivRate + "/-");
        result.add("Rs. " + (delivRate + returnedTotal) + "/-");
        return result;
    }

    public void paymentConfirmed(int buyerID, int orderID) {
        buyerLedger.getBuyerByID(buyerID).payForOrder(orderID);
    }

    // public void clearCart() {
    // (buyerLedger.getCurrentBuyer()).clearCart();
    // }

    // public float getLatestOrderBill() {
    // return (buyerLedger.getCurrentBuyer()).getLastOrderBill();
    // }

    // Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return buyerLedger.createBuyer(name, email, phone, password);
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        return productLedger.getProducts(filter, categories);// will automatically set in itself too
        // return productLedger.getProductLedger();//will return the set products
    }

    // Function that makes shipment and returns trackID
    // If an error occurs, it will return -1
    public int shipment(int buyerID, int orderID, String DeliverTo, String Address, String Phone, String Email) {
        // Buyer currBuyer = (Buyer)getCurrentUser();
        return buyerLedger.getBuyerByID(buyerID).shipment(orderID, DeliverTo, Address, Phone, Email);
        // int OId = ((Buyer)getCurrentUser()).getOrders().getLastOrder().getOrderID();
        // if(OId >0)
        // return ((Buyer)getCurrentUser()).shipment(OId, DeliverTo, Address, Phone,
        // Email);
        // else
        // return -1;
        // } else {
        // return -1;
        // }
    }

    public void cancelLatestOrder() {

    }

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

    public LinkedList<Order> getOrderHistory(int buyerID) {
        return buyerLedger.getBuyerByID(buyerID).getOrderHistory();
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
    // public void setCurrentProduct(int productID) {
    // productLedger.setCurrentProduct(productID);
    // }

    // public Product getCurrentProduct() {
    // return productLedger.getCurrentProduct();
    // }

    // public String getCurrentProductSeller() {
    // return productLedger.getProductSeller(productLedger.getCurrentProduct());
    // }

    public Buyer getCurrentUser() {
        return buyerLedger.getCurrentBuyer();
    }

    public void setCurrentUser(Buyer u) {
        // buyerLedger.setCurrentUser(u);
    }

    public LinkedList<SalesLineItem> getCartList(int buyerID) {
        return buyerLedger.getCartList(buyerID);
    }

    public void addToCart(int buyerID, int productID) {
        // userLedger.getCurrentUser().addToCart(p);
        // buyerLedger.addToCurrentUsersCart(productLedger.getProductByProductID(productID));
        buyerLedger.addToCart(buyerID, productLedger.getProductByProductID(productID));
    }

    public boolean updateBuyer(int buyerID, String name, String email, String password, String phone, String address) {
        return buyerLedger.updateBuyer(buyerID, name, email, password, phone, address);
    }

    public void logout() {
        // buyerLedger.setCurrentBuyer(null);
    }

    // public boolean deleteBuyer() {
    // return buyerLedger.deleteBuyer(getCurrentUser().getID());
    // }

    public boolean deleteBuyer(int buyerID) {
        return buyerLedger.deleteBuyer(buyerID);
    }

    // When browsing products, buyer may want to see average rating
    public float getAverageProductRating(int id) {
        return productLedger.getAverageRating(id);
    }

    public LinkedList<String> getProductInformation(int productID) {
        LinkedList<String> returnList = new LinkedList<String>();
        Product p = productLedger.getProductByProductID(productID);
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
        return ReviewLedger.createNewReview(reviewText, rating, userID, productID);
    }

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

    public float getCartTotal() {
        return 0;// TODO
    }

    // function returns true if the product is still in the cart after updating
    // and returns false if the product has been removed, i.e. quantity is now 0.
    public boolean updateCartItemQty(int buyerID, int productID, char updateType) {
        return buyerLedger.getBuyerByID(buyerID).updateCartQuantity(productID, updateType);
    }

}
