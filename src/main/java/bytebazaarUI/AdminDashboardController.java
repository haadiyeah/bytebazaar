package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private Button logoutBtn;

    @FXML
    void addNewFaq(ActionEvent event) throws IOException {
       //Redirecting to addfaq page
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(new URL("file:src/main/resources/bytebazaar/addfaq.fxml"));
       AddFAQController addFaqCtrl = new AddFAQController();
       loader.setController(addFaqCtrl);

       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = new Stage();
       stage.setTitle("ByteBazaar - the hardware Solution");
       stage.setScene(scene);
       stage.show();
       logoutBtn.getScene().getWindow().hide();
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

           // BusinessControllerFactory.getLoginControllerInst().logout(); // this will call logout on buyercontroller

            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Logout successfull");
            yay.setHeaderText("You are now logged out");
            yay.setContentText("You will be redirected shortly");
            yay.showAndWait();

            //Redirecting to welcome page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/welcomepg.fxml"));
            WelcomePgController welcomePgCtrl = new WelcomePgController();
            loader.setController(welcomePgCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("ByteBazaar - the hardware Solution");
            stage.setScene(scene);
            stage.show();
            logoutBtn.getScene().getWindow().hide();
        }
    }

    @FXML
    void viewAllBuyers(ActionEvent event) {

    }

    @FXML
    void viewAllSellers(ActionEvent event) {

    }

}
