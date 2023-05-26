package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Product;
import bytebazaar.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewingReviewsController implements Initializable {

    @FXML
    private Label averageRating;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Button profileBtn;

    @FXML
    private Label reviewAmountStars;

    @FXML
    private VBox reviewBox;

    @FXML
    private Label reviewPersonName;

    @FXML
    private Label reviewText;

    @FXML
    private VBox reviewsContainer;

    @FXML
    private Button submitbtn;

    @FXML
    private Button wishlistBtn;

    private Product currentprod;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("viewingproddetail");
    }

    @FXML
    void openCart(MouseEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    @FXML
    void openWishlist(ActionEvent event) {

    }

    @FXML
    void submitReview(ActionEvent event) throws IOException {
        App.setRoot("writereview");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.reviewsContainer.getChildren().remove(this.reviewBox);//removing the dummy data

        currentprod = BusinessControllerFactory.getBuyerControllerInst().getCurrentProduct();
        productImage.imageProperty().set(new Image(currentprod.getImageURL()));
        productName.setText(currentprod.getName());
        float avgRating = BusinessControllerFactory.getBuyerControllerInst()
                .getAverageProductRating(currentprod.getProductID());
        this.averageRating.setText("Average:" + avgRating);

        LinkedList<Review> reviewsToDisp = BusinessControllerFactory.getBuyerControllerInst()
                .getReviews(currentprod.getProductID());

        //For all reviews create the respective labels and add them to parent container
        for (int i = 0; i <reviewsToDisp.size(); i++) {
            VBox reviewBox=new VBox();
            Label reviewPersonName;
            Label reviewAmountStars;
            Label reviewText;

            reviewBox.setPrefHeight(126.0);
            reviewBox.setPrefWidth(591.0);
            reviewBox.setStyle("-fx-border-color: #c3efff;");
            reviewBox.setPadding(new Insets(5.0, 0, 0, 20.0));

            reviewPersonName = new Label(reviewsToDisp.get(i).getPersonName());
            reviewPersonName.setPrefHeight(27.0);
            reviewPersonName.setPrefWidth(533.0);
            reviewPersonName.setFont(Font.font("System Bold", FontWeight.BOLD, 14.0));


            reviewAmountStars = new Label("Rating given: " +reviewsToDisp.get(i).getRating()+"/5");
            reviewAmountStars.setPrefHeight(27.0);
            reviewAmountStars.setPrefWidth(533.0);
            reviewAmountStars.setFont(Font.font("System Bold", FontWeight.BOLD, 14.0));

            reviewText = new Label(reviewsToDisp.get(i).getReviewText());
            reviewText.setPrefHeight(70.0);
            reviewText.setPrefWidth(543.0);
            reviewText.setWrapText(true);

            reviewBox.getChildren().addAll(reviewPersonName, reviewAmountStars, reviewText);
            reviewsContainer.getChildren().add(reviewBox);
        }
    }

}
