package bytebazaar;

public class ReviewLedger {
    public static boolean createNewReview(String reviewText, int rating, int userID, int productID){
        Review review = new Review(reviewText, rating, userID, productID);
        return DBHandler.getInstance().save(review);

    }
}