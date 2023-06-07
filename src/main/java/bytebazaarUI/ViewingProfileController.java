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
import javafx.scene.control.Button ;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewingProfileController implements Initializable{
    int currentBuyerID;
    public void setData(int buyerID) {
        this.currentBuyerID=buyerID;
    }
    @FXML
    private Label address;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Button editProfBtn;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phoneNumber;

    @FXML
    private Button profileBtn;

    @FXML
    private Label recentOrderDate1;

    @FXML
    private Label recentOrdersTotalBill1;

    @FXML
    private Label recentorderdate3;

    @FXML
    private Label recentorderitems1;

    @FXML
    private Label recentorderitems2;

    @FXML
    private Label recentorderitems3;

    @FXML
    private Label recentordersdate2;

    @FXML
    private Label recentordertotalBill2;

    @FXML
    private Label recentordertotalbill3;

    @FXML
    private Button viewOrderHistoryBtn;

    @FXML
    private Button wishlistBtn;

    private LinkedList<Label> orderDates;
    private LinkedList<Label> orderItems;
    private LinkedList<Label> orderTotals;
    @FXML
    void goBack(ActionEvent event)throws IOException  {
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
    void openEditProfile(ActionEvent event) throws IOException {
        editProfBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/editprofile.fxml"));
        EditProfileController editProfileCtrl = new EditProfileController();
        editProfileCtrl.setData(currentBuyerID);
        loader.setController(editProfileCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openOrderHistory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingordersbuyer.fxml"));
        // WelcomePgController welcomePgCtrl = new WelcomePgController();
        ViewingOrdersBuyerController viewingOrdersCtrl = new ViewingOrdersBuyerController();
        viewingOrdersCtrl.setData(currentBuyerID);
        loader.setController(viewingOrdersCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("ByteBazaar - the hardware solution");
        stage.show();
        cartBtn1.getScene().getWindow().hide();
    }
    @FXML
    void openCart(ActionEvent event) throws IOException {
        cartBtn1.getScene().getWindow().hide();
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
    }
    @FXML
    void openWishList(ActionEvent event)  {
        System.out.println("wishlist btn clicked");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        orderDates = new LinkedList<Label>();
        orderItems= new LinkedList<Label>();
        orderTotals= new LinkedList<Label>();

        orderDates.add(recentOrderDate1);
        orderDates.add(recentordersdate2);
        orderDates.add(recentorderdate3);

        orderItems.add(recentorderitems1);
        orderItems.add(recentorderitems2);
        orderItems.add(recentorderitems3);

        orderTotals.add(recentOrdersTotalBill1);
        orderTotals.add(recentordertotalBill2);
        orderTotals.add(recentordertotalbill3);

        LinkedList<String> info=BusinessControllerManager.getBuyerControllerInst().getBuyerInfo(currentBuyerID);
        
        name.setText(info.get(0));
        email.setText(info.get(1));
        phoneNumber.setText(info.get(2));
        address.setText(info.get(4));

        LinkedList<LinkedList<String>> orderHist =  BusinessControllerManager.getBuyerControllerInst().getOrderHistory(currentBuyerID);
        int maxloops;
        //Since we are showing summary we will only display top 3
        if (orderHist.size() >= 3) {
            maxloops = 3;
        } else {
            maxloops = orderHist.size();
        }
        int i;
        for (i = 0; i < maxloops; i++) {
            orderDates.get(i).setText(orderHist.get(i).get(1));
            orderItems.get(i).setText(orderHist.get(i).get(4));
            orderTotals.get(i).setText(orderHist.get(i).get(3));
        }

        //clearing the rest
        while(i<3) {
            orderDates.get(i).setText("");
            orderItems.get(i).setText("");
            orderTotals.get(i).setText("");
            i++;
        }
    }

}
