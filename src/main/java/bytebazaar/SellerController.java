package bytebazaar;

public class SellerController {
    SellerLedger sellerLedger;

    public SellerController()
 {
    sellerLedger=new SellerLedger();
 }    
 public boolean login(String email, String password) {
        return sellerLedger.loginRequest(email, password);
    }

}
