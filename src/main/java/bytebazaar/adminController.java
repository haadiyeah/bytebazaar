package bytebazaar;

import java.sql.SQLException;
import java.util.LinkedList;

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

    public LinkedList<FAQ> getFAQs() {
        return faqledger.getFAQs();
    }

    public LinkedList<FAQ> findInFaq(String text) {
        return faqledger.findInFAQs(text);
    }

    public LinkedList<FAQ> getFAQledger() {
        return faqledger.getStoredFAQs();
    }

    
    
}
