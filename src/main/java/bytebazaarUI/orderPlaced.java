package bytebazaarUI;

import java.io.IOException;

import bytebazaar.App;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class orderPlaced {
    String A1;
    String A2;
    String A3;
    String A4;

    // orderPlaced(String A1, String A2, String A3, String A4) {
    // this.A1 = A1;
    // this.A2 = A2;
    // this.A3 = A3;
    // this.A4 = A4;
    // }

    @FXML
    private TextField a1;

    @FXML
    private TextField a2;

    @FXML
    private TextField a3;

    @FXML
    private TextField a4;

    public void initialize() {
        // a1.setText(A1);
        // a2.setText(A2);
        // a3.setText(A3);
        // a4.setText(A4);
    }

    @FXML
    private Button tO;

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

}
