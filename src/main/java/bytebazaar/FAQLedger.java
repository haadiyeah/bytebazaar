package bytebazaar;
import java.sql.SQLException;
import java.util.LinkedList;

public class FAQLedger {
    private LinkedList<FAQ> faqs;

    public FAQLedger(){
        faqs=new LinkedList<FAQ>();
    }

    public boolean addFAQ(String ques, String ans) throws SQLException {
        if ( DBHandler.getInstance().fetchAns(ques) == null ) {
            FAQ newFaq= new FAQ(ques, ans);

            DBHandler.getInstance().save_faq(newFaq);
            
            faqs.add(newFaq);
            return true;
        } else  {
            return false;
            //SHOW DUPLICATE FAQ MESSAGE
        }

    }
    
    
}
