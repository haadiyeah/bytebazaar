package bytebazaarUI;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
import javafx.fxml.Initializable;

public class trackController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Random random = new Random();
        int step = random.nextInt(4) + 1;

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

    @FXML
    void cancelOrder(ActionEvent event) {
        int orderID = BusinessControllerFactory.getBuyerControllerInst().getCurrentUser().getOrders().getLastOrder()
                .getOrderID();
        BusinessControllerFactory.getBuyerControllerInst().getCurrentUser().getOrders().removeOrder(orderID);
    }

    // hBox.setStyle("-fx-background-color: #75A81E;");

}
