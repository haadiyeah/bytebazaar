package bytebazaar;

public class BuyerController {
    BuyerLedger buyerledger;

    BuyerController() {
        buyerledger=new BuyerLedger();
    }

    //Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return buyerledger.createUser(name, email, phone, password);
    }
}
