package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.App;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class checkoutpage implements Initializable {

    @FXML
    private Button pO;

    public checkoutpage() {

    }

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
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("confirmO");
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        String A1 = nameTextField.getText();
        String A2 = addressTextField.getText();
        String A3 = phoneTextField.getText();
        String A4 = emailTextField.getText();

        // Function that returns tracking ID, if returns -1 it means an erorr
        if (BusinessControllerFactory.getBuyerControllerInst().shipment(A1, A2, A3, A4) == -1) {
            Alert err = new Alert(AlertType.ERROR);
            err.setHeaderText("An error occurred");
            err.showAndWait();
        }

        App.setRoot("orderPlaced");
    }

    @FXML
    void promoClicked(MouseEvent event) {

    }

    @FXML
    void voucherClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        float total = BusinessControllerFactory.getBuyerControllerInst().getLatestOrderBill();
        float deliveryFee = 190;// Set delivery fee in admin controller and fetch it
        float totalToPay = total + deliveryFee;
        itemsTotalLabel.setText("Rs. " + total + "/-");
        deliveryFeeLabel.setText("Rs. " + deliveryFee + "/-");
        totalToPayLabel.setText("Rs. " + totalToPay + "/-");
    }

}
