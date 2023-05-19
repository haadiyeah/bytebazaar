package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ViewingProdDetailController implements Initializable {
    Product currentprod;

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
    void addToCart(ActionEvent event) {
        BusinessControllerFactory.getBuyerControllerInst().addToCart(currentprod); 
       
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Added to cart successfully");
        alert.setContentText("You have added to cart");
        alert.showAndWait();
    }

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
       // App.setRoot("viewingprofile");
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        currentprod=BusinessControllerFactory.getBuyerControllerInst().getCurrentProduct();
        String sellerName = BusinessControllerFactory.getBuyerControllerInst().getCurrentProductSeller();
        mainImage.imageProperty().set(new Image(currentprod.getImageURL()));
        productName.setText(currentprod.getName());
        productPrice.setText("Rs. " + currentprod.getPrice() + "/-");
        productDesc.setText(currentprod.getDescription());
        companyName.setText(sellerName);
    }

}
