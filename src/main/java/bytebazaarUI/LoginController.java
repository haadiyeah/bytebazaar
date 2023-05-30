package bytebazaarUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;

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
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/welcomepg.fxml"));
            WelcomePgController welcomePgCtrl = new  WelcomePgController();
            loader.setController(welcomePgCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            loginBtn.getScene().getWindow().hide();
    }

    @FXML
    // Login button as BUYER pressed
    void performChecks(ActionEvent event) throws IOException {
        // //check if email and password match.
        int returningAccountID=BusinessControllerFactory.getBuyerControllerInst().loginRequest(emailText.getText(), passwordText.getText());
        
        if (returningAccountID!=-1) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Success");
            yay.setHeaderText("Congratulations, login success");
            yay.showAndWait();
            //App.setRoot("homepage");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/homepage.fxml"));
            HomepageController homepageCtrl = new HomepageController();
            homepageCtrl.setData(returningAccountID);
            loader.setController(homepageCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            loginBtn.getScene().getWindow().hide();

        } else {
            Alert warn = new Alert(AlertType.WARNING);
            warn.setTitle("Error");
            warn.setHeaderText("Login unsuccessful");
            warn.setContentText("We didn't find a matching record. Please check both your credentials and try again.");
            warn.showAndWait();
        }
    }

    @FXML
    void loginAsSeller(ActionEvent event) throws IOException {
        int returningAccountID=BusinessControllerFactory.getSellerControllerInst().login(emailText.getText(), passwordText.getText());
        if (returningAccountID!=-1) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Success");
            yay.setHeaderText("Congratulations, login success");
            yay.showAndWait();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/sellerdashboard.fxml"));
            SellerDashboardController sellerDashboardCtrl = new SellerDashboardController();
            sellerDashboardCtrl.setData(returningAccountID);
            loader.setController(sellerDashboardCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            loginBtn.getScene().getWindow().hide();

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
        int returningAccountID=BusinessControllerFactory.getAdminControllerInst().login(emailText.getText(), passwordText.getText());
        
        if (returningAccountID!=-1) {
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
