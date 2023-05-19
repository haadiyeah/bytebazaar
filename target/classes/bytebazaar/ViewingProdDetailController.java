package fxpractice;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ViewingProdDetailController {

    @FXML
    private HBox addToCartHbox;

    @FXML
    private HBox addToWishlistHbox;

    @FXML
    private Label addtoCartLabel;

    @FXML
    private Label addtoWishlistLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn;

    @FXML
    private Label companyName;

    @FXML
    private ImageView mainImage;

    @FXML
    private Label productDesc;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label productStockStatus;

    @FXML
    private Button profileBtn;

    @FXML
    private HBox reviewsBox;

    @FXML
    private Label reviewsSummary;

    @FXML
    private ImageView secondaryImg1;

    @FXML
    private ImageView secondaryImg2;

    @FXML
    private ImageView secondaryImg3;

    @FXML
    private Button viewCompanyProfileBtn;

    @FXML
    private Button viewReviewsBtn;
    
    @FXML
    private Button buyNowBtn;

    @FXML
    private Button wishlistBtn;

    
    @FXML
    void buyNow(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    void openCart(ActionEvent event) {

    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    @FXML
    void openWishlist(ActionEvent event) {

    }

    @FXML
    void viewCompanyProfile(ActionEvent event) {

    }

    @FXML
    void viewReviews(ActionEvent event) {

    }

}
