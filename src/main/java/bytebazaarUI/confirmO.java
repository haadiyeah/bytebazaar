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

public class confirmO {
    String A1;
    String A2;
    String A3;
    String A4;

    // confirmO(String A1, String A2, String A3, String A4) {
    // this.A1 = A1;
    // this.A2 = A2;
    // this.A3 = A3;
    // this.A4 = A4;
    // }

    @FXML
    private Button confirm;

    @FXML
    private Label sPM;

    @FXML
    void confirmOrder(ActionEvent event) throws IOException {
        // Load the selectPaymentMethod.fxml file
        App.setRoot("orderPlaced");

        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("orderPlaced.fxml"));

        // loader.setController(new orderPlaced(A1, A2, A3, A4));

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
    void selectPM(MouseEvent event) throws IOException {

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

}
