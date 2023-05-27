package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class payonline implements Initializable {

    String A1;
    String A2;
    String A3;
    String A4;

    @FXML
    private Button backBtn;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private Button cartBtn1;

    @FXML
    private TextField cvvTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label deliveryFeeLabel;

    @FXML
    private Label itemsTotalLabel;

    @FXML
    private TextField nameOnCardTextField;

    @FXML
    private Button profileBtn;


    @FXML
    private CheckBox saveCardCheckBox;

    @FXML
    private Label totalToPayLabel;

    @FXML
    private Button wishlistBtn;

    @FXML
    void goBack(ActionEvent event)throws IOException {
        App.setRoot("selectPaymentMethod");
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    // payonline(String A1, String A2, String A3, String A4) {
    // this.A1 = A1;
    // this.A2 = A2;
    // this.A3 = A3;
    // this.A4 = A4;
    // }

    @FXML
    private Button pN;

    @FXML
    private Label sPM;

    @FXML
    void payNow(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        App.setRoot("checkoutpage");
    }


    @FXML
    void selectPaymentMethod(MouseEvent event) throws IOException {
        App.setRoot("selectPaymentMethod");
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("selectPaymentMethod.fxml"));
        // loader.setController(new selectPaymentMethod(A1, A2, A3, A4));
        // Parent root = loader.load();
        // // Create a scene with the root node
        // Scene scene = new Scene(root);
        // // Get the stage from the event object
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // // Set the scene for the stage
        // stage.setScene(scene);
        // stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        float total = BusinessControllerFactory.getBuyerControllerInst().getLatestOrderBill();
        float deliveryFee=190;//Set delivery fee in admin controller and fetch it
        float totalToPay = total+deliveryFee; 
        itemsTotalLabel.setText("Rs. " + total + "/-");
        deliveryFeeLabel.setText("Rs. " + deliveryFee + "/-");
        totalToPayLabel.setText("Rs. " + totalToPay + "/-");
        
    }

}
