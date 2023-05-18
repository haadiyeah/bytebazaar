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

    public LinkedList<Product> getTopProducts() {
        productLedger.getTopProducts();//will automatically set in itself too
        return productLedger.getProductLedger();//will return the set top products
    }
}
