package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewingOrdersBuyerListController implements Initializable {
    int currentBuyerID;
    Order order;

    @FXML
    private Label orderPlacedDate;

    @FXML
    private Label orderPlacedDate1;

    @FXML
    private Button orderPlacedDetailButton;

    @FXML
    private HBox orderPlacedHBox;

    @FXML
    private Label orderPlacedID;

    @FXML
    private Label orderPlacedTime;

    @FXML
    void TrackDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/track.fxml"));
        trackController trackCtrl = new trackController();
        trackCtrl.setData(order.getOrderID(), order.getShipment().getTrackID(), currentBuyerID);
        loader.setController(trackCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        orderPlacedDetailButton.getScene().getWindow().hide();
    }

    public void setData(Order order, int currentBuyerID) {
        this.currentBuyerID = currentBuyerID;
        this.order = order;
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        orderPlacedDate.setText(order.getOrderDate().toString());
        // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        orderPlacedTime.setText(order.getOrderTime().toString());
        orderPlacedID.setText(Integer.toString(order.getOrderID()));
    }
}
