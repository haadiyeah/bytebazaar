package bytebazaar;

public class Review {
    private String reviewText;
    private int rating;
    private String personName;
    private int personID;
    private int productID;

    public Review(String reviewText, int rating, String personName, int productID) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.personName = personName;
        this.productID = productID;
        this.personID=0;
    }
    public Review(String reviewText, int rating, int personID, int productID) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.personName = personName;
        this.productID = productID;
        this.personID=personID;
        this.personName=null;
    }
    public String getReviewText() {
        return reviewText;
    }
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    
}
