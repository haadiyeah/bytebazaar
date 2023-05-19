package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Buyer;
import bytebazaar.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button ;
import javafx.scene.control.Label;

public class ViewingProfileController implements Initializable{
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
        App.setRoot("homepage");
    }

    @FXML
    void openEditProfile(ActionEvent event) throws IOException {
        App.setRoot("editprofile");
    }

    @FXML
    void openOrderHistory(ActionEvent event) {

    }
    @FXML
    void openCart(ActionEvent event) {

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

        Buyer currentBuyer = (Buyer)BusinessControllerFactory.getBuyerControllerInst().getCurrentUser();
        name.setText(currentBuyer.getName());
        email.setText(currentBuyer.getEmail());
        phoneNumber.setText(currentBuyer.getPhoneNum());
        address.setText(currentBuyer.getDeliveryDetails());

        LinkedList<Order> orderHist =  currentBuyer.getOrderHistory();
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
