package bytebazaar;

import java.util.LinkedList;

public class ProductLedger {
    private LinkedList<Product> productLedger;
   
    public ProductLedger() {
        productLedger=new LinkedList<Product>();
    }

    public LinkedList<Product> getProducts(String filter, LinkedList<String> categories) {
        productLedger.clear();
        productLedger = DBHandler.getInstance().getProducts(filter,  categories);
        return productLedger;
    }
    public LinkedList<Product> getProductLedger() {
        return productLedger;
    }

    public void setProductLedger(LinkedList<Product> productLedger) {
        this.productLedger = productLedger;
    }


    public Product getProductByProductID(int ID) {
        for(int i=0;i<productLedger.size();i++) {
            if(productLedger.get(i).getProductID() ==ID)
                return productLedger.get(i);
        }
        return null;
    }

    public String getProductSeller(Product prod) {
       return DBHandler.getInstance().getProductSeller(prod.getSellerID());
    }

    public float getAverageRating(int id){
        return DBHandler.getInstance().getAverageRating(id);
    }

    public LinkedList<Review> getReviews(int id) {
        return DBHandler.getInstance().getReviews(id);
    }


}
