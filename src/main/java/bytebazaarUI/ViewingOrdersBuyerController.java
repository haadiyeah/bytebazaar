package bytebazaarUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
import bytebazaar.Order;
import bytebazaar.OrderLedger;
import bytebazaar.SalesLineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewingOrdersBuyerController implements Initializable {
    private int currentBuyerID;
    private LinkedList<Order> o;

    public void setData(int buyerID) {
        this.currentBuyerID = buyerID;
        this.o = BusinessControllerFactory.getBuyerControllerInst().getOrders(buyerID);
    }

    @FXML
    private Button backBtn;

    @FXML
    private HBox headerHbox1;

    @FXML
    private VBox ordersPlacedContainerVbox;

    @FXML
    private Button profileBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/buyerdashboard.fxml"));
        // SellerDashboardController sellerDashboardCtrl = new
        // SellerDashboardController();
        // sellerDashboardCtrl.setData(currentBuyerID);
        // loader.setController(sellerDashboardCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (int i = 0; i < o.size(); i++) {
            Order oi = o.get(i);
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingordersbuyerlist.fxml"));
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                HBox hBox = fxmlLoader.load();
                ViewingOrdersBuyerListController vobl = new ViewingOrdersBuyerListController();
                vobl.setData(oi, currentBuyerID);
                fxmlLoader.setController(vobl);
                ordersPlacedContainerVbox.getChildren().add(hBox);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}
