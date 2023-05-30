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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckoutPageController implements Initializable {
    private int orderID;
    private int currentBuyerID;

    public void setData(int buyerID,int orderID) {
        this.orderID = orderID;
        this.currentBuyerID = buyerID;
    }

    @FXML
    private Button pO;

    @FXML
    private Label promo;

    @FXML
    private Label voucher;
    @FXML
    private TextField addressTextField;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Label deliveryFeeLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label itemsTotalLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button profileBtn;

    @FXML
    private Label totalToPayLabel;

    @FXML
    private Button wishlistBtn;

    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        if(nameTextField.getText().equals("") || addressTextField.getText().equals("") || phoneTextField.getText().equals("") || emailTextField.getText().equals("")) {
            Alert err = new Alert(AlertType.ERROR);
            err.setHeaderText("One or more fields is missing");
            err.showAndWait();
            return;
        }
        
        String A1 = nameTextField.getText();
        String A2 = addressTextField.getText();
        String A3 = phoneTextField.getText();
        String A4 = emailTextField.getText();

        //Function that returns tracking ID, if returns -1 it means an erorr
        if (BusinessControllerFactory.getBuyerControllerInst().shipment(currentBuyerID, orderID, A1, A2, A3, A4) == -1) {
            Alert err = new Alert(AlertType.ERROR);
            err.setHeaderText("An error occurred");
            err.showAndWait();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/orderPlaced.fxml"));
        OrderPlacedController orderPlacedCtrl = new OrderPlacedController();
        orderPlacedCtrl.setData( orderID,currentBuyerID);
        loader.setController(orderPlacedCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void promoClicked(MouseEvent event) {

    }

    @FXML
    void voucherClicked(MouseEvent event) {

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
