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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WritingReviewController implements Initializable  {
    int currentBuyerID;
    int currentProductID;

    public void setData(int buyerID, int productID) {
        this.currentBuyerID=buyerID;
        this.currentProductID=productID;
    }
    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn;

    @FXML
    private Label prodSeller;

    @FXML
    private Label productName;

    @FXML
    private Button profileBtn;

    @FXML
    private Slider ratingSlider;

    @FXML
    private TextArea reviewfield;

    @FXML
    private Button submitbtn;

    @FXML
    private Button wishlistBtn;
    
    @FXML
    private ImageView productImage;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        backBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingreviews.fxml"));
        ViewingReviewsController viewingReviewsCtrl = new ViewingReviewsController();
        viewingReviewsCtrl.setData(currentBuyerID, currentProductID);
        loader.setController(viewingReviewsCtrl);

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
        //Submit review sending text, rating slider amount, currentUser ID and currentProductID
       if ( BusinessControllerFactory.getBuyerControllerInst().submitReview(reviewfield.getText(), (int)ratingSlider.getValue(), currentBuyerID, currentProductID) ) {
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Review added successfully");
            alert.setHeaderText("You have submitted the review");
            alert.showAndWait();

            reviewfield.setText("");
            ratingSlider.setValue(0);

            //Going back
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingreviews.fxml"));
            ViewingReviewsController viewingReviewsCtrl = new ViewingReviewsController();
            viewingReviewsCtrl.setData(currentBuyerID, currentProductID);
            loader.setController(viewingReviewsCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } else {
        Alert alert=new Alert(AlertType.ERROR);
            alert.setHeaderText("Review wasn't added! Sorry");
            alert.setHeaderText("An error occurred");
            alert.showAndWait();

            reviewfield.setText("");
            ratingSlider.setValue(0);
       }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LinkedList<String> info = BusinessControllerFactory.getBuyerControllerInst().getProductInformation(currentProductID);
        productName.setText(info.get(0));
       //productImage=new ImageView(new Image(info.get(2)));
        //prodSeller=new Label();
        productImage.imageProperty().set(new Image(info.get(2)));
        prodSeller.setText(info.get(4));
    }

}
