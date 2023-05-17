package bytebazaar;
import java.sql.SQLException;
import java.util.LinkedList;

public class FAQLedger {
    private LinkedList<FAQ> faqs;

    public FAQLedger(){
        faqs=new LinkedList<FAQ>();
    }

    public void addFAQ(String ques, String ans) throws SQLException {
        //TODO: Check if db already has the faq
        FAQ newFaq= new FAQ(ques, ans);

        DBHandler.getInstance().save_faq(newFaq);
        
        faqs.add(newFaq);


    }
    
    
}
