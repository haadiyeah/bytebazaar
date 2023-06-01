package bytebazaar;

import java.util.LinkedList;

public class ProductLedger {
    private LinkedList<Product> productsList;
   
    public ProductLedger() {
        productsList=new LinkedList<Product>();
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        productsList.clear();
        productsList = DBHandler.getInstance().getProducts(filter,  categories);
        return productsList;
    }

    public void setProductsList(LinkedList<Product> productLedger) {
        this.productsList = productLedger;
    }

    public LinkedList<Product> search(String text){
        LinkedList<Product> resultsToDisplay = new LinkedList<Product>();
        productsList.forEach(product -> {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                resultsToDisplay.add(product);
            }
        });
        return resultsToDisplay;
    }


    public Product getProductByProductID(int ID) {
        for(int i=0;i<productsList.size();i++) {
            if(productsList.get(i).getProductID() ==ID)
                return productsList.get(i);
        }
        return null;
    }

    public String getProductSellerName(Product prod) {
       return DBHandler.getInstance().getProductSeller(prod.getSellerID());
    }

    public float getAverageRating(int id){
        return DBHandler.getInstance().getAverageRating(id);
    }

    public LinkedList<Review> getReviews(int id) {
        return DBHandler.getInstance().getReviews(id);
    }

    public void addProduct(Product p) {
        productsList.add(p);
    }

}
