package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class orderPlaced implements Initializable {
    String A1;
    String A2;
    String A3;
    String A4;

    @FXML
    private Label DeliveryPhoneLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Label deliveryAddressLabel;

    @FXML
    private Label deliveryEmailLabel;

    @FXML
    private Label deliveryFeeLabel;

    @FXML
    private Label deliveryNameLabel;

    @FXML
    private Label itemsTotalLabel;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Button profileBtn;
    @FXML
    private Label totalToPayLabel;

    @FXML
    private HBox trackOrderBtn;

    @FXML
    private Button wishlistBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }


    // orderPlaced(String A1, String A2, String A3, String A4) {
    // this.A1 = A1;
    // this.A2 = A2;
    // this.A3 = A3;
    // this.A4 = A4;
    // }
    @FXML
    private Button tO;

    public void initialize() {
        // a1.setText(A1);
        // a2.setText(A2);
        // a3.setText(A3);
        // a4.setText(A4);
    }

   

    @FXML
    void trackOrder(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        App.setRoot("track");

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("track.fxml"));

        // loader.setController(new trackController()); // A1, A2, A3, A4

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
        
        LinkedList<String> info = BusinessControllerFactory.getBuyerControllerInst().getLatestOrderInfo();
        orderIDLabel.setText(info.get(0));
        deliveryNameLabel.setText(info.get(1));
        deliveryAddressLabel.setText(info.get(2));
        DeliveryPhoneLabel.setText(info.get(3));
        deliveryEmailLabel.setText(info.get(4));
    }

}
