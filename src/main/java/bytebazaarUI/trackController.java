package bytebazaarUI;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class trackController {

    @FXML
    private HBox Delivered;

    @FXML
    private HBox PaymentPending;

    @FXML
    private HBox Processing;

    @FXML
    private HBox Shipped;

    @FXML
    private Button cancel;

    @FXML
    void cancelOrder(ActionEvent event) {

    }

    public trackController() {
        Random random = new Random();
        int step = random.nextInt(4);
        ;

        if (step == 4) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
            Shipped.setStyle("-fx-background-color:  #75A81E;");
            Delivered.setStyle("-fx-background-color:  #75A81E;");
        } else if (step == 3) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
            Shipped.setStyle("-fx-background-color:  #75A81E;");
        } else if (step == 2) {
            PaymentPending.setStyle("-fx-background-color:  #75A81E;");
            Processing.setStyle("-fx-background-color:  #75A81E;");
        }

    }

    // hBox.setStyle("-fx-background-color: #75A81E;");

}
