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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class selectPaymentMethod {
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

}
