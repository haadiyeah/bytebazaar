package bytebazaar;

import java.sql.SQLException;

public class adminController {
    AdminLedger adminLedger;
    FAQLedger faqledger;
    float deliveryRate;

    public adminController(){
        faqledger=new FAQLedger();
        adminLedger=new AdminLedger();
        deliveryRate=190;
    }

    public boolean addFAQ(String ques, String ans){
        try {
            return faqledger.addFAQ(ques, ans);
        } catch (SQLException sqle) {
           return false;
        }
    }

    public int login(String email, String password) {
        return adminLedger.loginRequest(email, password);
    }

    public float getDeliveryRate() {
        return deliveryRate;
    }

    

    
}
