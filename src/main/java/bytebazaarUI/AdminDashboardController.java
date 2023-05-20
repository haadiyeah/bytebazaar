package bytebazaarUI;

import java.io.IOException;
import java.util.Optional;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AdminDashboardController {

    @FXML
    void addNewFaq(ActionEvent event) throws IOException {
        App.setRoot("addfaq");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Log out?");
        confirm.setHeaderText("Do you want to log out?");
        confirm.showAndWait();

        Optional<ButtonType> result = confirm.showAndWait();
        if (!result.isPresent() || result.get() == ButtonType.CANCEL) {
            // Not logged out, show message?
        } else if (result.get() == ButtonType.OK) {

            BusinessControllerFactory.getLoginControllerInst().logout(); // this will call logout on buyercontroller

            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Logout successfull");
            yay.setHeaderText("You are now logged out");
            yay.setContentText("You will be redirected shortly");
            yay.showAndWait();
            App.setRoot("welcomepg");
        }
    }

    @FXML
    void viewAllBuyers(ActionEvent event) {

    }

    @FXML
    void viewAllSellers(ActionEvent event) {

    }

}
