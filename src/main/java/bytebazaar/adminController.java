package bytebazaar;

import java.sql.SQLException;
import java.util.LinkedList;

public class adminController {
    AdminLedger adminLedger;
    FAQLedger faqledger;

    public adminController(){
        faqledger=new FAQLedger();
        adminLedger=new AdminLedger();
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


    
}
