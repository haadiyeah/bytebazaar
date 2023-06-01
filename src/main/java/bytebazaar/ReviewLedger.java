package bytebazaar;

import java.util.LinkedList;

public class ReviewLedger {
    private LinkedList<Review> reviewLedger;
    
    public boolean createNewReview(String reviewText, int rating, int userID, int productID){
        Review review = new Review(reviewText, rating, userID, productID);
        reviewLedger.add(review);
        return DBHandler.getInstance().saveReview(review);
    }
}
