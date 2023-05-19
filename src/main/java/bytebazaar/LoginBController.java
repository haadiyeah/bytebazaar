package bytebazaar;

//business controller for login
public class LoginBController {
    UserLedger userLedger;

    // SellerLedger buyerledger = new SellerLedger();

    public LoginBController() {
        userLedger = new UserLedger();
    }

    public boolean login(String email, String password, String userType) {
        return userLedger.loginRequest(email, password, userType);
    }

    public User getCurrentUser() {
        return userLedger.getCurrentUser();
    }
}
