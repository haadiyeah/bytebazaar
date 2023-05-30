package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePgController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private StackPane welcomeSideBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setNode(welcomeSideBox);
        Duration dur= Duration.millis(2000);
        fadeIn.setDuration( dur );
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    @FXML
    void openLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/login.fxml"));
        LoginController loginCtrl = new  LoginController();
        loader.setController(loginCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loginBtn.getScene().getWindow().hide();
    }

    @FXML
    void openSignup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/signup.fxml"));
        SignupController signupCtrl = new  SignupController();
        loader.setController(signupCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loginBtn.getScene().getWindow().hide();
    }

}

