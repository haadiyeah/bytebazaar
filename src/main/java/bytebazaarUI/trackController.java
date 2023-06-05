package bytebazaarUI;

import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerManager;
import javafx.fxml.Initializable;

public class trackController implements Initializable {
    private int orderID;
    private int currentBuyerID;

    public void setData(int orderID, int buyerID) {
        this.orderID = orderID;
        this.currentBuyerID = buyerID;
    }

    
    @FXML
    private HBox Delivered;

    @FXML
    private HBox PaymentPending;

    @FXML
    private HBox Processing;

    @FXML
    private HBox Shipped;

    @FXML
    private Button cancel;
    @FXML
    private Button backBtn;


    @FXML
    private Button cartBtn1;

    @FXML
    private Label deliveryFeeLabel;

    @FXML
    private Label itemsTotalLabel;

    @FXML
    private Button profileBtn;

    @FXML
    private Label totalToPayLabel;

    @FXML
    private Button wishlistBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Random random = new Random();
        int step = random.nextInt(4) + 1;

        if (step == 4) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
            Shipped.setStyle("-fx-background-color:  #75A81E;");
            Delivered.setStyle("-fx-background-color:  #75A81E;");
        } else if (step == 3) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
            Shipped.setStyle("-fx-background-color:  #75A81E;");
        } else if (step == 2) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
        }
    }

    @FXML
    void cancelOrder(ActionEvent event) {
        //Displaying confirmation message
        Alert warn = new Alert(AlertType.WARNING);
        warn.setHeaderText("Cancel order?");
        warn.setContentText("If you press OK, your order will be cancelled. Are you sure?");
        Optional<ButtonType> result = warn.showAndWait();

        if (result.get() == ButtonType.OK) {
            // Cancelling the order
            BusinessControllerManager.getBuyerControllerInst().cancelOrder(currentBuyerID, orderID);
        }
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
        cartBtn1.getScene().getWindow().hide();
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
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
        profileBtn.getScene().getWindow().hide();
    }

    // hBox.setStyle("-fx-background-color: #75A81E;");

}
