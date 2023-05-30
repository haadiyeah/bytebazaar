package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
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

    public void setData(int buyerID) {
        this.currentBuyerID = buyerID;
    }

    @FXML
    private Button backBtn;

    @FXML
    private Label deliveryInfoAddress;

    @FXML
    private Label deliveryInfoEmail;

    @FXML
    private Label deliveryInfoName;

    @FXML
    private Label deliveryInfoPhone;

    @FXML
    private HBox headerHbox;

    @FXML
    private HBox headerHbox1;

    @FXML
    private Label infoAboutOrderHeaderLabel;

    @FXML
    private VBox orderDetailContainerVbox;

    @FXML
    private Label orderDetailProductAmount;

    @FXML
    private Label orderDetailProductName;

    @FXML
    private Label orderDetailProductPrice;

    @FXML
    private Label orderDetailProductQty;

    @FXML
    private HBox orderDetailitemHbox;

    @FXML
    private Label orderPlacedAmount;

    @FXML
    private Label orderPlacedBuyer;

    @FXML
    private Label orderPlacedDate;

    @FXML
    private Button orderPlacedDetailButton;

    @FXML
    private HBox orderPlacedHBox;

    @FXML
    private Label orderPlacedID;

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

    @FXML
    void viewOrderDetails(ActionEvent event) {
        // Button buttonClicked = (Button) event.getTarget();
        // int orderid = Integer.parseInt(buttonClicked.getId().split("-")[1]);

        // LinkedList<LinkedList<String>> returnedInfo =
        // BusinessControllerFactory.getBuyerControllerInst().getCurrentUser().getOrders();
        // setOrderDetailsView(returnedInfo);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}
