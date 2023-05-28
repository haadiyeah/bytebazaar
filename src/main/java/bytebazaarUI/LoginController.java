package bytebazaarUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;

public class LoginController {
    // public LoginController(String usertype) {
    // this.userType=usertype;
    // }

    @FXML
    private Button backBtn;

    @FXML
    private TextField emailText;

    @FXML
    private Button loginBtn;
    @FXML
    private Button loginBtn1;

    @FXML
    private PasswordField passwordText;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("selectview");
    }

    @FXML
    // Login button as BUYER pressed
    void performChecks(ActionEvent event) throws IOException {
        // //check if email and password match.
        if (BusinessControllerFactory.getBuyerControllerInst().loginRequest(emailText.getText(), passwordText.getText())) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Success");
            yay.setHeaderText("Congratulations, login success");
            yay.showAndWait();
            App.setRoot("homepage");
        } else {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setTitle("Error");
            warn.setHeaderText("Login unsuccessful");
            warn.showAndWait();
        }
    }

    @FXML
    void loginAsSeller(ActionEvent event) {
        if (BusinessControllerFactory.getSellerControllerInst().login(emailText.getText(), passwordText.getText())) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Success");
            yay.setHeaderText("Congratulations, login success");
            yay.showAndWait();
        } else {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setTitle("Error");
            warn.setHeaderText("Login unsuccessful");
            warn.setContentText("Please check your credentials and ensure they are correct");
            warn.showAndWait();
        }
    }

    @FXML
    void loginAsAdmin(ActionEvent event) throws IOException {
        if (BusinessControllerFactory.getAdminControllerInst().login(emailText.getText(), passwordText.getText())) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Success");
            yay.setHeaderText("Congratulations, login success");
            yay.showAndWait();
            App.setRoot("admindashboard");
        } else {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setTitle("Error");
            warn.setHeaderText("Login unsuccessful");
            warn.setContentText("Please check your credentials and ensure they are correct");
            warn.showAndWait();
        }
    }

    



}
