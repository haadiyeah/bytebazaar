package bytebazaarUI;

import java.io.IOException;

import bytebazaar.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class checkoutpage {

    @FXML
    private TextField a1;
    String A1;

    @FXML
    private TextField a2;
    String A2;

    @FXML
    private TextField a3;
    String A3;

    @FXML
    private TextField a4;
    String A4;

    @FXML
    private Button pO;

    @FXML
    private Label promo;

    @FXML
    private Label voucher;

    @FXML
    void address(ActionEvent event) {

    }

    @FXML
    void deliveryName(ActionEvent event) {

    }

    @FXML
    void email(ActionEvent event) {

    }

    @FXML
    void phone(ActionEvent event) {

    }

    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        A1 = a1.getText();
        A2 = a2.getText();
        A3 = a3.getText();
        A4 = a4.getText();

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("selectPaymentMethod.fxml"));

        //loader.setController(new selectPaymentMethod(A1, A2, A3, A4));

        //Parent root = loader.load();

        // Create a scene with the root node
        //Scene scene = new Scene(root);

        App.setRoot("selectPaymentMethod");
        // Get the stage from the event object
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the scene for the stage
        //stage.setScene(scene);
        //stage.show();
    }

    @FXML
    void promoClicked(MouseEvent event) {

    }

    @FXML
    void voucherClicked(MouseEvent event) {

    }

}
