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

    //This function populates the current userLedger with ALL the FAQs present in the database.
    public LinkedList<FAQ> populateFAQs(){
        LinkedList<FAQ> retList = DBHandler.getInstance().getFAQs();
        if(retList != null) {
            faqs=retList;
            return retList;
        } else {
            return null;
        }
    }

    public LinkedList<FAQ> getStoredFAQs(){
        return faqs;
    }


    public LinkedList<FAQ> findInFAQs(String text) {
        LinkedList<FAQ> retfaqs = new LinkedList<FAQ>();
        for(int i=0;i<faqs.size();i++) {
            if((faqs.get(i).getQuestion().toLowerCase().contains(text.toLowerCase())) || (faqs.get(i).getAnswer().toLowerCase().contains(text.toLowerCase()))){
                retfaqs.add(faqs.get(i));
            }
        }
        if(retfaqs.isEmpty()) {
            return null;
        } else {
            return retfaqs;
        }
    }
    
    
}
