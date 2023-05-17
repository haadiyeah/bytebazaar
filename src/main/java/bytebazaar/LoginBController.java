package bytebazaar;

public class LoginBController {
    BuyerLedger buyerledger ;
    //SellerLedger buyerledger = new SellerLedger();

    public LoginBController() {
        buyerledger=new BuyerLedger();
    }

    public boolean login(String email, String password, String userType) {
        if(userType=="Buyer")
            return buyerledger.loginRequest(email, password, userType);
        //TODO add seller option
        else 
        return false;
    }
}
