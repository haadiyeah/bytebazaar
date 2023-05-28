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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class WritingReviewController implements Initializable  {

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
        App.setRoot("viewingreviews");
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
    void submitReview(ActionEvent event) {
        //Submit review sending text, rating slider amount, currentUser ID and currentProductID
       if ( BusinessControllerFactory.getBuyerControllerInst().submitReview(reviewfield.getText(), (int)ratingSlider.getValue(), BusinessControllerFactory.getBuyerControllerInst().getCurrentUser().getID(), BusinessControllerFactory.getBuyerControllerInst().getCurrentProduct().getProductID()) ) {
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Review added successfully");
            alert.setHeaderText("You have the review");
            alert.showAndWait();

            reviewfield.setText("");
            ratingSlider.setValue(0);
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
        Product currentprod=BusinessControllerFactory.getBuyerControllerInst().getCurrentProduct();
        String sellerName = BusinessControllerFactory.getBuyerControllerInst().getCurrentProductSeller();
        productImage.imageProperty().set(new Image(currentprod.getImageURL()));
        productName.setText(currentprod.getName());
        prodSeller.setText(sellerName);
    }

}
