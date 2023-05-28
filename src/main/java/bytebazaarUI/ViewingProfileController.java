package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Buyer;
import bytebazaar.Order;
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
    void openOrderHistory(ActionEvent event) {
        //Redirect to a page showing complete order history
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

        LinkedList<String> info=BusinessControllerFactory.getBuyerControllerInst().getBuyerInfo(currentBuyerID);
        
        name.setText(info.get(0));
        email.setText(info.get(1));
        phoneNumber.setText(info.get(2));
        address.setText(info.get(4));

        LinkedList<Order> orderHist =  BusinessControllerFactory.getBuyerControllerInst().getOrderHistory(currentBuyerID);
        int maxloops;
        if(orderHist.size()>=3) {
            maxloops=3;
        } else {
            maxloops=orderHist.size();
        }
        int i;
        for(i=0;i<maxloops;i++) {
            orderDates.get(i).setText( orderHist.get(i).getOrderDate().toString() );
            orderItems.get(i).setText(""+orderHist.get(i).getTotalItems());
            orderTotals.get(i).setText(""+orderHist.get(i).getTotalBill());
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
