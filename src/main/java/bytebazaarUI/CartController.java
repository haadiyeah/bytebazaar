package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.SalesLineItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CartController implements Initializable{
    @FXML
    private Label CartItem1;

    @FXML
    private Label CartItem11;

    @FXML
    private Label CartItem12;

    @FXML
    private Label CartItem121;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private VBox cartVbox;

    @FXML
    private Button checkoutBtn;

    @FXML
    private HBox headerHbox;

    @FXML
    private Label productName;

    @FXML
    private Label productName1;

    @FXML
    private Button profileBtn;

    @FXML
    private Label totalAmount;

    @FXML
    private Button wishlistBtn;


    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    void gotoCheckout(ActionEvent event) {
        //Mahad
    }

    @FXML
    void openCart(ActionEvent event) {
        //do nothing u are already in cart
    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LinkedList<SalesLineItem> cartList = new LinkedList<SalesLineItem>();
        cartList= BusinessControllerFactory.getBuyerControllerInst().getCartList();
        
        if(cartList!=null) {
            cartList.forEach(cartItem -> {
                HBox cartitemHbox = new HBox();

                Label cartItemName = new Label(cartItem.getProductName());
                cartItemName.setFont(new Font(14.0));
                cartItemName.setContentDisplay(ContentDisplay.RIGHT);
                cartItemName.setPrefWidth(270.0);
                cartItemName.setPrefHeight(34.0);
                cartItemName.setPadding(new Insets(0, 0, 0, 20.0));

                Label cartItemPrice = new Label("Rs. " + cartItem.getPrice());
                cartItemPrice.setFont(new Font(14.0));
                cartItemPrice.setContentDisplay(ContentDisplay.RIGHT);
                cartItemPrice.setPrefWidth(102.0);
                cartItemPrice.setPrefHeight(34.0);
                cartItemPrice.setPadding(new Insets(0, 0, 0, 10.0));
               
                Label cartItemQuantity = new Label("" + cartItem.getQuantity());
                cartItemQuantity.setFont(new Font(14.0));
                cartItemQuantity.setContentDisplay(ContentDisplay.RIGHT);
                cartItemQuantity.setPrefWidth(99.0);
                cartItemQuantity.setPrefHeight(34.0);
                cartItemQuantity.setPadding(new Insets(0, 0, 0, 20.0));

                Label cartItemAmount = new Label("" + cartItem.getPrice()*cartItem.getQuantity());
                cartItemAmount.setFont(new Font(14.0));
                cartItemAmount.setContentDisplay(ContentDisplay.RIGHT);
                cartItemAmount.setPrefWidth(98.0);
                cartItemAmount.setPrefHeight(34.0);
                cartItemAmount.setPadding(new Insets(0, 0, 0, 20.0));
        
                cartitemHbox.getChildren().addAll(cartItemName, cartItemPrice, cartItemQuantity, cartItemAmount);
        
                cartVbox.getChildren().add(cartitemHbox);

            });

            float total=0;
             for(int i=0;i<cartList.size();i++) {
                 total+= cartList.get(i).getPrice();
             }
             totalAmount.setText("Rs. " + total + "/-");
        } else {
            totalAmount.setText("Rs. 0.00/-");
        }
        
    }

}
