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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class selectPaymentMethod implements Initializable {
    String A1;
    String A2;
    String A3;
    String A4;

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
    void cOD(MouseEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file

        App.setRoot("confirmO");

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmO.fxml"));

        // loader.setController(new confirmO(A1, A2, A3, A4));

        // Parent root = loader.load();

        // // Create a scene with the root node
        // Scene scene = new Scene(root);

        // // Get the stage from the event object
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // // Set the scene for the stage
        // stage.setScene(scene);
        // stage.show();
    }

    @FXML
    void cODButton(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        App.setRoot("confirmO");

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmO.fxml"));

        // loader.setController(new confirmO(A1, A2, A3, A4));

        // Parent root = loader.load();

        // // Create a scene with the root node
        // Scene scene = new Scene(root);

        // // Get the stage from the event object
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // // Set the scene for the stage
        // stage.setScene(scene);
        // stage.show();
    }

    @FXML
    void payNow(ActionEvent event) {

    }

    @FXML
    void payOnline(MouseEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file

        App.setRoot("payonline");

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("payonline.fxml"));

        // loader.setController(new payonline(A1, A2, A3, A4));

        // Parent root = loader.load();

        // // Create a scene with the root node
        // Scene scene = new Scene(root);

        // // Get the stage from the event object
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // // Set the scene for the stage
        // stage.setScene(scene);
        // stage.show();
    }

    @FXML
    void payOnlineButton(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file

        App.setRoot("payonline");

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("payonline.fxml"));

        // loader.setController(new payonline(A1, A2, A3, A4));

        // Parent root = loader.load();

        // // Create a scene with the root node
        // Scene scene = new Scene(root);

        // // Get the stage from the event object
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // // Set the scene for the stage
        // stage.setScene(scene);
        // stage.show();
    }

    @FXML
    void selctPaymentMethod(MouseEvent event) {

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

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

}
