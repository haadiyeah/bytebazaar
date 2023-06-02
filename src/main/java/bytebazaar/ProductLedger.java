package bytebazaar;

import java.util.HashMap;
import java.util.LinkedList;

public class ProductLedger {
    private LinkedList<Product> productsList;
    private  HashMap<String, Integer> mapCategories;
   
   
    public ProductLedger() {
        productsList=new LinkedList<Product>();
        mapCategories = new HashMap<String, Integer>();
        mapCategories.put("Keyboards", 1);
        mapCategories.put("Mice", 2);
        mapCategories.put("Monitors", 3);
        mapCategories.put("Graphic cards", 4);
        mapCategories.put("Controllers", 5);
        mapCategories.put("Laptops", 6);
        mapCategories.put("PCs", 7);    
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

    public boolean addNewProduct(int sellerID, String name, float price, int qty, String imgUrl, String desc, String category) {
        int categoryID = mapCategories.get(category);
        int productID = DBHandler.getInstance().saveProduct(sellerID, name, price, qty, imgUrl, desc, categoryID);
        if (productID != -1) {
            Product p = new Product(productID, price, name, sellerID, imgUrl, desc, categoryID);
            this.productsList.add(p);
            return true;
        } else {
            return false;
        }

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
