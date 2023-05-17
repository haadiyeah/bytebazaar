package bytebazaar;

public class BuyerController {
    UserLedger userLedger;

    BuyerController() {
        userLedger=new UserLedger();
    }

    //Initially the default account will be buyer
    public int signup(String name, String phone, String email, String password) {
        return userLedger.createUser(name, email, phone, password);
    }
}
