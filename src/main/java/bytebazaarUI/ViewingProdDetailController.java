package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewingProdDetailController implements Initializable {
    // Product currentprod;

    int currentProdID;
    int currentBuyerID;

    public void setData(int buyerid, int productid) {
        this.currentProdID = productid;
        this.currentBuyerID = buyerid;
    }

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
        BusinessControllerFactory.getBuyerControllerInst().addToCart(currentBuyerID, currentProdID);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Added to cart successfully");
        alert.setContentText("You have added to cart");
        alert.showAndWait();
    }

    @FXML
    void buyNow(ActionEvent event) throws IOException {
        int orderID = BusinessControllerFactory.getBuyerControllerInst().buyNow(currentBuyerID, currentProdID, 1);
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/selectPaymentMethod.fxml"));
            SelectPaymentMethodController selectPaymentCtrl = new SelectPaymentMethodController();
            selectPaymentCtrl.setData(orderID, currentBuyerID);
            loader.setController(selectPaymentCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            buyNowBtn.getScene().getWindow().hide();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/homepage.fxml"));
        HomepageController homepageCtrl = new HomepageController();
        homepageCtrl.setData(currentBuyerID);
        loader.setController(homepageCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
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
    void viewCompanyProfile(ActionEvent event) {

    }

    @FXML
    void viewReviews(ActionEvent event) throws IOException {
        viewReviewsBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingreviews.fxml"));
        ViewingReviewsController viewingReviewsCtrl = new ViewingReviewsController();
        viewingReviewsCtrl.setData(currentBuyerID, currentProdID);
        loader.setController(viewingReviewsCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LinkedList<String> info = BusinessControllerFactory.getBuyerControllerInst()
                .getProductInformation(currentProdID);
        productName.setText(info.get(0));
        productDesc.setText(info.get(1));
        mainImage.imageProperty().set(new Image(info.get(2)));
        productPrice.setText(info.get(3));
        companyName.setText(info.get(4));
        reviewsSummary.setText(info.get(5));
    }

}
