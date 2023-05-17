package bytebazaar;

import java.sql.SQLException;

public class adminController {
    FAQLedger faqledger;

    public adminController(){
        faqledger=new FAQLedger();
    }

    public void addFAQ(String ques, String ans){
        try {
            faqledger.addFAQ(ques, ans);
        } catch (SQLException sqle) {
            //TO-DO: RETURN AN ERROR
        }
    }
    
}
