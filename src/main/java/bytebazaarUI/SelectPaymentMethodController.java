package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectPaymentMethodController implements Initializable {

    private int orderID;
    private int currentBuyerID;

    public void setData(int orderID, int buyerID) {
        this.orderID = orderID;
        this.currentBuyerID = buyerID;
    }

    // selectPaymentMethod(String A1, String A2, String A3, String A4) {
    // this.A1 = A1;
    // this.A2 = A2;
    // this.A3 = A3;
    // this.A4 = A4;
    // }

    // selectPaymentMethod() {
    // this.A1 = "A1";
    // this.A2 = "A2";
    // this.A3 = "A3";
    // this.A4 = "A4";
    // }

    @FXML
    private Label SPM;

    @FXML
    private Button cODb;

    @FXML
    private Label cod;

    @FXML
    private Button pN;

    @FXML
    private Label pO;

    @FXML
    private Button pOB;

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

    @FXML
    void cODButton(ActionEvent event) throws IOException {
        //Going to the checkout page.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/checkoutpage.fxml"));
        CheckoutPageController checkoutPageCtrl = new CheckoutPageController();
        checkoutPageCtrl.setData(currentBuyerID, orderID);
        loader.setController(checkoutPageCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void payNow(ActionEvent event) {

    }

    @FXML
    void payOnline(MouseEvent event) throws IOException {
        //App.setRoot("payonline");

    }

    @FXML
    void payOnlineButton(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/payonline.fxml"));
            PayOnlineController payOnlineCtrl = new PayOnlineController ();
            payOnlineCtrl.setData(orderID,currentBuyerID);
            loader.setController(payOnlineCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            backBtn.getScene().getWindow().hide();
    }

    @FXML
    void selctPaymentMethod(MouseEvent event) {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LinkedList<String> info = BusinessControllerFactory.getBuyerControllerInst().getOrderSummary(currentBuyerID, orderID);
        itemsTotalLabel.setText(info.get(0));
        deliveryFeeLabel.setText(info.get(1));
        totalToPayLabel.setText(info.get(2));
    }

    //The below function will first check if the user really wants to exist the process.
    //If yes, it will cancel the curent order.
    private boolean attemptToExit() {
        // Showing a confirmation message.
        Alert warn = new Alert(AlertType.WARNING);
        warn.setHeaderText("Cancel order?");
        warn.setContentText("If you exit this screen, your order will be cancelled. Are you sure?");
        Optional<ButtonType> result = warn.showAndWait();

        if (result.get() == ButtonType.OK) {
            // Cancelling the order
            BusinessControllerFactory.getBuyerControllerInst().cancelOrder(currentBuyerID, orderID);
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if (attemptToExit()) {
            // Changing screen
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
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        if (attemptToExit()) {
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
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        if(attemptToExit()){
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
    }

}
