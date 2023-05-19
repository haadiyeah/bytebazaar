package fxpractice;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button ;

public class ViewingProfileController {
    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Button editProfBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button viewOrderHistoryBtn;

    @FXML
    private Button wishlistBtn;

    @FXML
    void openCart(ActionEvent event) {

    }



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
    void openWishList(ActionEvent event)  {
        System.out.println("wishlist btn clicked");
    }


}
