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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CartController implements Initializable {
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
    private Label cartItemAmount;

    @FXML
    private Label cartItemName;

    @FXML
    private Label cartItemPrice;

    @FXML
    private Label cartItemQuantity;

    @FXML
    private VBox cartVbox;

    @FXML
    private HBox cartitemHbox;

    @FXML
    private Button checkoutBtn;

    @FXML
    private Button decreaseQtyButton;

    @FXML
    private HBox headerHbox;

    @FXML
    private Button increaseQtyButton;

    @FXML
    private Label productName;

    @FXML
    private Label productName1;

    @FXML
    private Label productName11;

    @FXML
    private Label totalAmount;

    @FXML
    private Button profileBtn;

    @FXML
    private Button wishlistBtn;
    
    private LinkedList<SalesLineItem> cartList;
    private LinkedList<Label> quantities;
    private LinkedList<Label> amounts;
    private LinkedList<HBox> boxes;

    @FXML
    void decreaseQty(ActionEvent event) {
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        cartList.get(id).setQuantity( cartList.get(id).getQuantity() -1 );
        if(cartList.get(id).getQuantity() == 0) {
            cartList.remove(id); 
            //boxes.get(id).setVisible(false);
            //cartVbox.getChildren().remove(id);
            //ORRRR 
            cartVbox.getChildren().remove(boxes.get(id));
        } else {
            quantities.get(id).setText(""+cartList.get(id).getQuantity());
            amounts.get(id).setText(""+cartList.get(id).getPrice()*cartList.get(id).getQuantity());
        }
        updateTotal();
        
    }

    @FXML
    void increaseQty(ActionEvent event) {
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        cartList.get(id).setQuantity( cartList.get(id).getQuantity() +1 );
        quantities.get(id).setText(""+cartList.get(id).getQuantity());
        amounts.get(id).setText(""+cartList.get(id).getPrice()*cartList.get(id).getQuantity());
        updateTotal();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    void gotoCheckout(ActionEvent event) {
        // Mahad
    }

    @FXML
    void openCart(ActionEvent event) {
        // do nothing u are already in cart
    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    private void updateTotal() {
        float total=0;
        for(int i=0;i<cartList.size();i++) {
            total+= cartList.get(i).getQuantity()*cartList.get(i).getPrice();
        }
        totalAmount.setText("Rs. " + total + "/-");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.cartVbox.getChildren().remove(this.cartitemHbox);//removing the dummy data

        //Initializing necessary lists;
        cartList = new LinkedList<SalesLineItem>();
        cartList = BusinessControllerFactory.getBuyerControllerInst().getCartList();
        boxes=new LinkedList<HBox>();
        quantities = new LinkedList<Label>();
        amounts = new LinkedList<Label>();

        float total=0;
        //Setting up the cart and calculating the total alongside.
        if (cartList != null) {
            for(int i=0;i<cartList.size();i++) {
                // Create HBox
                HBox cartitemHbox = new HBox();
                cartitemHbox.setAlignment(Pos.CENTER);
                cartitemHbox.setPrefHeight(34.0);
                cartitemHbox.setPrefWidth(566.0);
                boxes.add(cartitemHbox);

                // Create Labels
                Label cartItemName = new Label(cartList.get(i).getProductName());
                cartItemName.setContentDisplay(ContentDisplay.RIGHT);
                cartItemName.setPrefHeight(34.0);
                cartItemName.setPrefWidth(290.0);
                cartItemName.setFont(new Font(14.0));
                cartItemName.setPadding(new Insets(0, 0, 0, 20.0));

                Label cartItemPrice = new Label("" + cartList.get(i).getPrice());
                cartItemPrice.setContentDisplay(ContentDisplay.RIGHT);
                cartItemPrice.setPrefHeight(34.0);
                cartItemPrice.setPrefWidth(98.0);
                cartItemPrice.setFont(new Font(14.0));
                cartItemPrice.setPadding(new Insets(0, 0, 0, 10.0));
                HBox.setMargin(cartItemPrice, new Insets(0));

                Label cartItemQuantity = new Label("" + cartList.get(i).getQuantity());
                quantities.add(cartItemQuantity);
                cartItemQuantity.setContentDisplay(ContentDisplay.RIGHT);
                cartItemQuantity.setPrefHeight(34.0);
                cartItemQuantity.setPrefWidth(39.0);
                cartItemQuantity.setFont(new Font(14.0));
                cartItemQuantity.setPadding(new Insets(0, 0, 0, 10.0));

                Label cartItemAmount = new Label("" + cartList.get(i).getQuantity()*cartList.get(i).getPrice());
                amounts.add(cartItemAmount);
                cartItemAmount.setContentDisplay(ContentDisplay.RIGHT);
                cartItemAmount.setPrefHeight(34.0);
                cartItemAmount.setPrefWidth(98.0);
                cartItemAmount.setFont(new Font(14.0));
                cartItemAmount.setPadding(new Insets(0, 0, 0, 20.0));
                total+= cartList.get(i).getQuantity()*cartList.get(i).getPrice();

                // Create Buttons
                Button increaseQtyButton = new Button("+");
                increaseQtyButton.setId("increaseQtyButton-"+i); // Setting id to access later
                increaseQtyButton.setOnAction(e -> increaseQty(e));
                increaseQtyButton.setMnemonicParsing(false);
                increaseQtyButton.setPrefHeight(25.0);
                increaseQtyButton.setPrefWidth(27.0);

                Button decreaseQtyButton = new Button("-");
                decreaseQtyButton.setId("decreaseQtyButton-"+i); // Setting id to access later
                decreaseQtyButton.setOnAction(e -> decreaseQty(e));
                decreaseQtyButton.setMnemonicParsing(false);
                decreaseQtyButton.setPrefHeight(25.0);
                decreaseQtyButton.setPrefWidth(26.0);

                //Add components to HBox
                cartitemHbox.getChildren().addAll(cartItemName, cartItemPrice, increaseQtyButton, cartItemQuantity,
                        decreaseQtyButton, cartItemAmount);

                //Add to external-most vbox
                cartVbox.getChildren().add(cartitemHbox);

            }

            totalAmount.setText("Rs. " + total + "/-");
        } else {
            totalAmount.setText("Rs. 0.00/-");
        }

    }

}
