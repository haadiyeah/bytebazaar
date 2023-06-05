package bytebazaarUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerManager;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewingOrdersBuyerController implements Initializable {
    private int currentBuyerID;
    private LinkedList<Order> o;

    public void setData(int buyerID) {
        this.currentBuyerID = buyerID;
        this.o = BusinessControllerManager.getBuyerControllerInst().getOrders(buyerID);
        if(this.o ==null) {
            System.out.println("returned null");
        }
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
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingprofile.fxml"));
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
        try {
            for (int i = 0; i < o.size(); i++) {
                Order oi = o.get(i);
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingordersbuyerlist.fxml"));
                ViewingOrdersBuyerListController vobl = new ViewingOrdersBuyerListController();
                vobl.setData(oi, currentBuyerID);
                fxmlLoader.setController(vobl);
                HBox hBox = fxmlLoader.load();
                ordersPlacedContainerVbox.getChildren().add(hBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}
