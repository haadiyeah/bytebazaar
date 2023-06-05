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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewingOrdersSellerController implements Initializable {
    private int currentSellerID;

    public void setData(int sellerID) {
        this.currentSellerID = sellerID;
    }

    @FXML
    private Button backBtn;

    @FXML
    private VBox orderDetailContainerVbox;

    @FXML
    private VBox ordersPlacedContainerVbox;

    @FXML
    private HBox orderDetailitemHbox;

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
    private Label orderDetailProductAmount;

    @FXML
    private Label orderDetailProductName;

    @FXML
    private Label orderDetailProductPrice;

    @FXML
    private Label orderDetailProductQty;

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
    private Button profileBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/sellerdashboard.fxml"));
        SellerDashboardController sellerDashboardCtrl = new SellerDashboardController();
        sellerDashboardCtrl.setData(currentSellerID);
        loader.setController(sellerDashboardCtrl);

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
        Button buttonClicked = (Button) event.getTarget();
        int orderid = Integer.parseInt(buttonClicked.getId().split("-")[1]);

        //Setting the header
        infoAboutOrderHeaderLabel.setText("Info about order # "+orderid);

        //Setting the info in the detailed orders view
        LinkedList<LinkedList<String>> returnedInfo = BusinessControllerManager.getSellerControllerInst()
                .getOrderDetails(currentSellerID, orderid);
        setOrderDetailsView(returnedInfo);

        //Setting the info in the delivery details
        LinkedList<String> deliveryInfo = BusinessControllerManager.getSellerControllerInst().getShipmentDetails(currentSellerID, orderid);
        deliveryInfoName.setText(deliveryInfo.get(0));
        deliveryInfoAddress.setText(deliveryInfo.get(1));
        deliveryInfoEmail.setText(deliveryInfo.get(2));
        deliveryInfoPhone.setText(deliveryInfo.get(3));
        
        
        if(returnedInfo.size() == 0 ) {
            System.out.println("\n\nempty list returned in vuew order detail");
        }

    }

    public void setOrderDetailsView(LinkedList<LinkedList<String>> info) {
        // Clearing the dummy data
        orderDetailContainerVbox.getChildren().remove(this.orderDetailitemHbox);

        for (int i = 0; i < info.size(); i++) {
            HBox orderDetailitemHbox = new HBox();
            orderDetailitemHbox.setAlignment(Pos.CENTER_LEFT);
            orderDetailitemHbox.setPrefHeight(34.0);
            orderDetailitemHbox.setPrefWidth(566.0);

            Label orderDetailProductName = new Label(info.get(i).get(0));
            orderDetailProductName.setContentDisplay(ContentDisplay.RIGHT);
            orderDetailProductName.setPrefHeight(34.0);
            orderDetailProductName.setPrefWidth(132.0);
            orderDetailProductName.setTextFill(Color.web("#000000"));
            orderDetailProductName.setWrapText(true);
            orderDetailProductName.setFont(new Font(11.0));
            orderDetailProductName.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderDetailProductQty = new Label(info.get(i).get(1));
            orderDetailProductQty.setContentDisplay(ContentDisplay.RIGHT);
            orderDetailProductQty.setPrefHeight(34.0);
            orderDetailProductQty.setPrefWidth(60.0);
            orderDetailProductQty.setTextFill(Color.web("#000000"));
            orderDetailProductQty.setWrapText(true);
            orderDetailProductQty.setFont(new Font(11.0));
            orderDetailProductQty.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderDetailProductPrice = new Label(info.get(i).get(2));
            orderDetailProductPrice.setContentDisplay(ContentDisplay.RIGHT);
            orderDetailProductPrice.setPrefHeight(34.0);
            orderDetailProductPrice.setPrefWidth(81.0);
            orderDetailProductPrice.setTextFill(Color.web("#000000"));
            orderDetailProductPrice.setWrapText(true);
            orderDetailProductPrice.setFont(new Font(11.0));
            orderDetailProductPrice.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderDetailProductAmount = new Label(info.get(i).get(3));
            orderDetailProductAmount.setContentDisplay(ContentDisplay.RIGHT);
            orderDetailProductAmount.setPrefHeight(34.0);
            orderDetailProductAmount.setPrefWidth(89.0);
            orderDetailProductAmount.setTextFill(Color.web("#000000"));
            orderDetailProductAmount.setWrapText(true);
            orderDetailProductAmount.setFont(new Font(11.0));
            orderDetailProductAmount.setPadding(new Insets(0, 0, 0, 20.0));

            orderDetailitemHbox.getChildren().addAll(orderDetailProductName, orderDetailProductQty,
                    orderDetailProductPrice, orderDetailProductAmount);

            orderDetailContainerVbox.getChildren().add(orderDetailitemHbox);
        }
    }

    // Each item in the list is a list of strings for each order.
    // This will be used for the "orders placed" box
    public void setData(LinkedList<LinkedList<String>> data) {
        // Clearing the dummy data
        ordersPlacedContainerVbox.getChildren().remove(this.orderPlacedHBox);

        // inserting the data
        for (int i = 0; i < data.size(); i++) {
            HBox orderPlacedHBox = new HBox();
            orderPlacedHBox.setAlignment(Pos.CENTER_LEFT);
            orderPlacedHBox.setPrefHeight(34.0);
            orderPlacedHBox.setPrefWidth(566.0);


            Label orderPlacedID = new Label(data.get(i).get(0));
            orderPlacedID.setContentDisplay(ContentDisplay.RIGHT);
            orderPlacedID.setPrefHeight(34.0);
            orderPlacedID.setPrefWidth(71.0);
            orderPlacedID.setFont(new Font(14.0));
            orderPlacedID.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderPlacedBuyer = new Label(data.get(i).get(1));
            orderPlacedBuyer.setContentDisplay(ContentDisplay.RIGHT);
            orderPlacedBuyer.setPrefHeight(34.0);
            orderPlacedBuyer.setPrefWidth(141.0);
            orderPlacedBuyer.setFont(new Font(14.0));
            orderPlacedBuyer.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderPlacedAmount = new Label(data.get(i).get(2));
            orderPlacedAmount.setContentDisplay(ContentDisplay.RIGHT);
            orderPlacedAmount.setPrefHeight(34.0);
            orderPlacedAmount.setPrefWidth(131.0);
            orderPlacedAmount.setFont(new Font(14.0));
            orderPlacedAmount.setPadding(new Insets(0, 0, 0, 20.0));

            Label orderPlacedDate = new Label(data.get(i).get(3));
            orderPlacedDate.setContentDisplay(ContentDisplay.RIGHT);
            orderPlacedDate.setPrefHeight(34.0);
            orderPlacedDate.setPrefWidth(158.0);
            orderPlacedDate.setFont(new Font(14.0));
            orderPlacedDate.setPadding(new Insets(0, 0, 0, 20.0));

            Button orderPlacedDetailButton = new Button("Details");
            orderPlacedDetailButton.setId("button-" + data.get(i).get(0));
            orderPlacedDetailButton.setMnemonicParsing(false);
            orderPlacedDetailButton.setOnAction(e -> viewOrderDetails(e));
            orderPlacedDetailButton.setPrefHeight(25.0);
            orderPlacedDetailButton.setPrefWidth(61.0);

            orderPlacedHBox.getChildren().addAll(orderPlacedID, orderPlacedBuyer, orderPlacedAmount, orderPlacedDate,
                    orderPlacedDetailButton);

            ordersPlacedContainerVbox.getChildren().add(orderPlacedHBox);

        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setData(BusinessControllerManager.getSellerControllerInst().getOrdersData(currentSellerID));
    }

}
