package bytebazaar;

import java.sql.SQLException;

public class adminController {
    FAQLedger faqledger;

    public adminController(){
        faqledger=new FAQLedger();
    }

    public boolean addFAQ(String ques, String ans){
        try {
            return faqledger.addFAQ(ques, ans);
        } catch (SQLException sqle) {
           return false;
        }
    }
    
}
