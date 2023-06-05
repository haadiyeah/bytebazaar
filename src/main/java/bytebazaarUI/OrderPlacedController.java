package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import bytebazaar.BusinessControllerManager;
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

public class OrderPlacedController implements Initializable {
    private int orderID;
    private int currentBuyerID;

    public void setData(int orderID, int buyerID) {
        this.orderID = orderID;
        this.currentBuyerID = buyerID;
    }

    @FXML
    private Label DeliveryPhoneLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Label deliveryAddressLabel;

    @FXML
    private Label deliveryEmailLabel;

    @FXML
    private Label deliveryFeeLabel;

    @FXML
    private Label deliveryNameLabel;

    @FXML
    private Label itemsTotalLabel;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Button profileBtn;
    @FXML
    private Label totalToPayLabel;

    @FXML
    private HBox trackOrderBtn;

    @FXML
    private Button wishlistBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/homepage.fxml"));
        HomepageController homepageCtrl = new HomepageController();
        homepageCtrl.setData(currentBuyerID);
        loader.setController(homepageCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/cart.fxml"));
        CartController cartCtrl = new CartController();
        cartCtrl.setData(currentBuyerID);
        loader.setController(cartCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        cartBtn1.getScene().getWindow().hide();
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingprofile.fxml"));
        ViewingProfileController viewingProfileCtrl = new ViewingProfileController();
        viewingProfileCtrl.setData(currentBuyerID);
        loader.setController(viewingProfileCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        profileBtn.getScene().getWindow().hide();
    }

    @FXML
    private Button tO;

    @FXML
    void trackOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/track.fxml"));
        trackController trackCtrl = new trackController();
        trackCtrl.setData( orderID, currentBuyerID);
        loader.setController(trackCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        profileBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LinkedList<String> info = BusinessControllerManager.getBuyerControllerInst().getOrderSummary(currentBuyerID,
                orderID);
        itemsTotalLabel.setText(info.get(0));
        deliveryFeeLabel.setText(info.get(1));
        totalToPayLabel.setText(info.get(2));

        LinkedList<String> detail = BusinessControllerManager.getBuyerControllerInst()
                .getOrderDeliveryDetails(currentBuyerID, orderID);
        orderIDLabel.setText(detail.get(0));
        deliveryNameLabel.setText(detail.get(1));
        deliveryAddressLabel.setText(detail.get(2));
        DeliveryPhoneLabel.setText(detail.get(3));
        deliveryEmailLabel.setText(detail.get(4));
    }

}
