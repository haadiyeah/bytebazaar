package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
import bytebazaar.SalesLineItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CartController implements Initializable {
    int currentBuyerID;

    public void setData(int id) {
        this.currentBuyerID=id;
    }
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

    //private LinkedList<SalesLineItem> cartList;
    private LinkedList<Label> quantities;
    private LinkedList<Label> amounts;
    private LinkedList<HBox> boxes;
    private LinkedList<Integer> cartItemsIDs;

    @FXML
    void decreaseQty(ActionEvent event) {
        //Checking which button was pressed
        Button numberButton = (Button) event.getTarget();
        int index = Integer.parseInt(numberButton.getId().split("-")[1]);
        int productID= cartItemsIDs.get(index);
        LinkedList<SalesLineItem> cartList;

        //Update the qty in the backend:
        //If this returns true it means the product still exists in the cart i.e. not 0
        boolean returnStatus=BusinessControllerFactory.getBuyerControllerInst().updateCartItemQty(currentBuyerID, productID, '-');
        //Getting the update cart
        cartList = BusinessControllerFactory.getBuyerControllerInst().getCartList(currentBuyerID);

        if (returnStatus==false ){//Product is no longer in the cart
            cartVbox.getChildren().remove(boxes.get(index));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("You have removed the item from cart");
            alert.showAndWait();
        } else {
            //getting the updated value from the cart list
            quantities.get(index).setText(""+cartList.get(index).getQuantity());
            amounts.get(index).setText(""+cartList.get(index).getSubTotal());
        }

        //Updating the 'total' label at the bottom
        totalAmount.setText("Rs. " + updateTotal(cartList) + "/-");
    }

    @FXML
    void increaseQty(ActionEvent event) {
        //This code will give the index,  (0,1,2,3...) NOT productid, 
        //of the product in the cart, whose button is clicked.
        Button numberButton = (Button) event.getTarget();
        int index = Integer.parseInt(numberButton.getId().split("-")[1]);
        int productID= cartItemsIDs.get(index);
        
        //Update the qty in the backend:
        BusinessControllerFactory.getBuyerControllerInst().updateCartItemQty(currentBuyerID, productID, '+');
        LinkedList<SalesLineItem> cartList = BusinessControllerFactory.getBuyerControllerInst().getCartList(currentBuyerID);
        
        //getting the updated value from the cart list
        quantities.get(index).setText(""+cartList.get(index).getQuantity());
        amounts.get(index).setText(""+cartList.get(index).getSubTotal());

        //Updating the 'total' label at the bottom
        totalAmount.setText("Rs. " + updateTotal(cartList) + "/-");
    }

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
    void gotoCheckout(ActionEvent event) throws IOException {
        //The function below creates an order , clears cart and returns the orderID.
        //We save it to pass to the next controller.
        int orderID = BusinessControllerFactory.getBuyerControllerInst().buyNow(currentBuyerID);
        
        //pass orderid forward
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/selectPaymentMethod.fxml"));
        SelectPaymentMethodController selectPaymentCtrl = new SelectPaymentMethodController();
        selectPaymentCtrl.setData(orderID, currentBuyerID);
        loader.setController(selectPaymentCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        profileBtn.getScene().getWindow().hide();
       
    }

    @FXML
    void openCart(ActionEvent event) {
        // do nothing u are already in cart
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        profileBtn.getScene().getWindow().hide();
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
    }

    private float updateTotal(LinkedList<SalesLineItem> cartList) {
        float total=0;
        for(int i=0;i<cartList.size();i++) {
            total+= cartList.get(i).getSubTotal();
        }
        return total;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.cartVbox.getChildren().remove(cartitemHbox); //Removing the dummy data
        // Initializing necessary lists;
        cartItemsIDs = new LinkedList<Integer>();
        LinkedList<SalesLineItem> cartList = BusinessControllerFactory.getBuyerControllerInst().getCartList(currentBuyerID);
        boxes=new LinkedList<HBox>();
        quantities = new LinkedList<Label>();
        amounts = new LinkedList<Label>();

        setCart(cartList);

    }

    public void setCart(LinkedList<SalesLineItem> cartList) {
        this.cartVbox.getChildren().removeAll(); //Removes the dummy Data
        float total=0;
        //Setting up the cart and calculating the total alongside.
        if (cartList != null) {
            for (int i = 0; i < cartList.size(); i++) {
                //Adding to list of productIDs, these will be used for reference later.
                cartItemsIDs.add(cartList.get(i).getProductID());

                HBox cartitemHbox = new HBox();
                cartitemHbox.setAlignment(Pos.CENTER);
                cartitemHbox.setPrefHeight(34.0);
                cartitemHbox.setPrefWidth(566.0);
                boxes.add(cartitemHbox);

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

                Label cartItemAmount = new Label("" + cartList.get(i).getQuantity() * cartList.get(i).getPrice());
                amounts.add(cartItemAmount);
                cartItemAmount.setContentDisplay(ContentDisplay.RIGHT);
                cartItemAmount.setPrefHeight(34.0);
                cartItemAmount.setPrefWidth(98.0);
                cartItemAmount.setFont(new Font(14.0));
                cartItemAmount.setPadding(new Insets(0, 0, 0, 20.0));
                total+= cartList.get(i).getQuantity()*cartList.get(i).getPrice();

                Button increaseQtyButton = new Button("+");
                increaseQtyButton.setId("increaseQtyButton-" + i);
                increaseQtyButton.setOnAction(e -> increaseQty(e));
                increaseQtyButton.setMnemonicParsing(false);
                increaseQtyButton.setPrefHeight(25.0);
                increaseQtyButton.setPrefWidth(27.0);

                Button decreaseQtyButton = new Button("-");
                decreaseQtyButton.setId("decreaseQtyButton-" + i); 
                decreaseQtyButton.setOnAction(e -> decreaseQty(e));
                decreaseQtyButton.setMnemonicParsing(false);
                decreaseQtyButton.setPrefHeight(25.0);
                decreaseQtyButton.setPrefWidth(26.0);

                //Adding to the hbox
                cartitemHbox.getChildren().addAll(cartItemName, cartItemPrice, increaseQtyButton, cartItemQuantity,
                        decreaseQtyButton, cartItemAmount);

                //adding ot the container(vbox scrollpane) 
                cartVbox.getChildren().add(cartitemHbox);

                total += cartList.get(i).getSubTotal();

            }

            totalAmount.setText("Rs. " + total + "/-");
        } else {
            totalAmount.setText("Rs. 0.00/-");
        }
    }

}
