package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewingReviewsController implements Initializable {
    int currentBuyerID;
    int currentProductID;

    public void setData(int buyerID, int productID) {
        this.currentBuyerID = buyerID;
        this.currentProductID = productID;
    }

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

    @FXML
    void goBack(ActionEvent event) throws IOException {
        backBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingproddetail.fxml"));
        ViewingProdDetailController prodDetailCtrl = new ViewingProdDetailController();
        prodDetailCtrl.setData(currentBuyerID, currentProductID);
        loader.setController(prodDetailCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openCart(MouseEvent event) throws IOException {
        cartBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/cart.fxml"));
        CartController cartCtrl = new CartController();
        cartCtrl.setData(currentBuyerID);
        loader.setController(cartCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        profileBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingprofile.fxml"));
        ViewingProfileController viewingProfileCtrl = new ViewingProfileController();
        viewingProfileCtrl.setData(currentBuyerID);
        loader.setController(viewingProfileCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openWishlist(ActionEvent event) {

    }

    @FXML
    void submitReview(ActionEvent event) throws IOException {
        if (!BusinessControllerFactory.getBuyerControllerInst().hasOrderedProduct(currentBuyerID, currentProductID)){
            Alert err = new Alert(AlertType.ERROR);
            err.setHeaderText("Sorry, you are not eligible to write a review");
            err.setContentText("You must have purchased a product to be eligible to write a review.");
            err.showAndWait();
            return;
        }
        submitbtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/writereview.fxml"));
        WritingReviewController writingReviewCtrl = new WritingReviewController();
        writingReviewCtrl.setData(currentBuyerID, currentProductID);
        loader.setController(writingReviewCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.reviewsContainer.getChildren().remove(this.reviewBox);// removing the dummy data

        LinkedList<String> info = BusinessControllerFactory.getBuyerControllerInst()
                .getProductInformation(currentProductID);
        productName.setText(info.get(0));
        productImage.imageProperty().set(new Image(info.get(2)));

        float avgRating = BusinessControllerFactory.getBuyerControllerInst()
                .getAverageProductRating(currentProductID);
        this.averageRating.setText("Average:" + avgRating);

        LinkedList<Review> reviewsToDisp = BusinessControllerFactory.getBuyerControllerInst()
                .getReviews(currentProductID);

        if (reviewsToDisp == null) {
            VBox noreviewsBox = new VBox();
            Label noreviewsText;

            noreviewsBox.setPrefHeight(126.0);
            noreviewsBox.setPrefWidth(591.0);
            noreviewsBox.setStyle("-fx-border-color: #c3efff;");
            noreviewsBox.setPadding(new Insets(5.0, 0, 0, 20.0));

            noreviewsText = new Label("No reviews yet! Why not leave your own?");
            noreviewsText.setPrefHeight(27.0);
            noreviewsText.setPrefWidth(533.0);
            noreviewsText.setFont(Font.font("System Bold", FontWeight.BOLD, 14.0));

            noreviewsBox.getChildren().addAll(noreviewsText);
            reviewsContainer.getChildren().add(noreviewsBox);
        } else {

            // For all reviews create the respective labels and add them to parent container
            for (int i = 0; i < reviewsToDisp.size(); i++) {
                VBox reviewBox = new VBox();
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

                reviewAmountStars = new Label("Rating given: " + reviewsToDisp.get(i).getRating() + "/5");
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

}
