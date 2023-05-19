package bytebazaarUI;

import java.io.IOException;
import java.util.Optional;

import bytebazaar.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditProfileController {

    @FXML
    private CheckBox Check;

    @FXML
    private TextField addressText;

    @FXML
    private Button backBtn;

    @FXML
    private TextField emailText;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField phoneText;

    @FXML
    private Button saveChangesBtn;
    @FXML
    private Button backbtn;

    // @FXML
    // void deleteAcc(ActionEvent event) {
    //     Alert confirm = new Alert(AlertType.WARNING);
    //         confirm.setTitle("Warning!");
    //         confirm.setHeaderText("Are you sure you want to delete your account?");
    //         confirm.setContentText("This is a permanent action and cannot be undone!");
    //         confirm.showAndWait();
    // }

    @FXML
    void performChecks(ActionEvent event) throws IOException {
        String errorMessage = "";

        // check email field
        if (!(emailText.getText().contains("@") && emailText.getText().contains("."))) {
            errorMessage += "Invalid email format\n";
        }

        // check password for length and for special characters.
        String check = passwordText.getText();

        if (!(check.contains("@") || check.contains("!") || check.contains("&") || check.contains("#")
                || check.contains("%") || check.contains("^") || check.contains("*") || check.contains("?"))) {
            errorMessage += "Password must contain atleast 1 special character\n";
        }
        if (passwordText.getText().length() < 5) {
            errorMessage += "Password length must be 5 or more characters\n";
        }

        // checking the phone number ; making it a required field and enforcing digits only
        if (phoneText == null) {
            errorMessage += "Phone number (Required field) missing\n";
        } else {
            try {
                Integer.parseInt(phoneText.getText());
            } catch (NumberFormatException nfe) {
                errorMessage += "Invalid phone number\n";
            }
        }

        // Display error message if necessary
        if (errorMessage != "") {
            Alert err = new Alert(AlertType.ERROR);
            err.setTitle("Oops!");
            err.setHeaderText("Please resolve the following");
            err.setContentText(errorMessage);
            err.showAndWait();
        }
        // Successful signup, display confirmation
        else {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Profile changes!");
            confirm.setHeaderText("Do you want to save the changes?");
            confirm.showAndWait();

            Optional<ButtonType> result = confirm.showAndWait();
            if(!result.isPresent()){
                // alert is exited, no button has been pressed.
            }
            else if(result.get() == ButtonType.OK) {
                App.setRoot("viewingprofile");
            }
            else if(result.get() == ButtonType.CANCEL) {
                Alert warn = new Alert(AlertType.WARNING);
                warn.setTitle("Changes not saved");
                warn.setHeaderText("Changes not saved");
                warn.showAndWait();
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }


    @FXML
    private Button cartBtn;


    @FXML
    private Button profileBtn;


    @FXML
    void openCart(ActionEvent event) {

    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @FXML
    void openWishlist(ActionEvent event) {

    }


}
