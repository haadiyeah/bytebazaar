package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.App;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
        App.setRoot("login");
    }

    @FXML
    void openSignup(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }

}

