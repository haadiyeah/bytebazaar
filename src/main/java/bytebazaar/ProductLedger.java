package bytebazaar;

import java.util.LinkedList;

public class ProductLedger {
    private LinkedList<Product> productLedger;
    
   
    public ProductLedger() {
        productLedger=new LinkedList<Product>();
    }

    public LinkedList<Product> getTopProducts() {
        productLedger.clear();
        productLedger = DBHandler.getInstance().getTopSellingProducts();
        return productLedger;
    }
    public LinkedList<Product> getProductLedger() {
        return productLedger;
    }

    public void setProductLedger(LinkedList<Product> productLedger) {
        this.productLedger = productLedger;
    }

}
