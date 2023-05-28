package bytebazaarUI;

import java.io.IOException;
import java.util.Optional;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupController {

    @FXML
    private PasswordField confirmPassTextBox;

    @FXML
    private TextField emailTextBox;

    @FXML
    private PasswordField passTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private Button signUpButton;

    @FXML
    private CheckBox termsConditionsCheckBox;

    @FXML
    void performChecks(ActionEvent event) throws IOException {
        String errorMessage = "";

        // check email field
        // enforcing required field
        if (emailTextBox == null) {
            errorMessage += "Email (Required field) missing\n";
        } else {
            // checking format
            if (!(emailTextBox.getText().contains("@") && emailTextBox.getText().contains("."))) {
                errorMessage += "Please ensure @ and . is included in email\n";
            }
            if (emailTextBox.getText().contains(".")) {
                int i = emailTextBox.getText().indexOf('.');
                if (emailTextBox.getText().charAt(i + 1) == '.' || emailTextBox.getText().charAt(i - 1) == '.') {
                    errorMessage += "Please ensure two full stops are not consecutive in email\n";
                }
            }
        }

        // If the contents of Pass and ConfirmPass are equal and not empty, check for
        // special characters.
        if (passTextBox.getText().equals(confirmPassTextBox.getText()) && passTextBox.getText() != "") {
            String check = passTextBox.getText();

            if (!(check.contains("@") || check.contains("!") || check.contains("&") || check.contains("#")
                    || check.contains("%") || check.contains("^") || check.contains("*") || check.contains("?"))) {
                errorMessage += "Password must contain atleast 1 special character\n";
            }

            if (passTextBox.getText().length() < 5 || passTextBox.getText().length() > 15) {
                errorMessage += "Password length must be minimum 5 and maximum 15 characters\n";
            }
        } else {
            errorMessage += "Passwords do not match, re-check password fields\n";
        }

        // checking the phone number ; making it a required field and enforcing digits
        // only
        if (phoneTextBox == null) {
            errorMessage += "Phone number (Required field) missing\n";
        } else {
            try {
                Integer.parseInt(phoneTextBox.getText());
            } catch (NumberFormatException nfe) {
                errorMessage += "Invalid phone number\n";
            }
        }

        // ensure terms and conditions are checked
        if (!termsConditionsCheckBox.isSelected()) {
            errorMessage += "Please accept terms and conditions\n";
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
            confirm.setTitle("Welcome to ByteBazaar");
            confirm.setHeaderText("Please review these details and click OK to confirm");
            String info = "";
            info += "Email: " + emailTextBox.getText();
            info += "\nPhone: " + phoneTextBox.getText();
            confirm.setContentText(info);
            confirm.showAndWait();

            Optional<ButtonType> result = confirm.showAndWait();
            if (!result.isPresent()) {
                // alert is exited, no button has been pressed. do nothing
            } else if (result.get() == ButtonType.OK) {
                //The below function call will return the newly created userID
                //Can be used to pass
                if (BusinessControllerFactory.getBuyerControllerInst().signup("", phoneTextBox.getText(),
                        emailTextBox.getText(), passTextBox.getText()) > 0) {
                    Alert yay = new Alert(AlertType.INFORMATION);
                    yay.setTitle("Success");
                    yay.setHeaderText("Congratulations, your account has been created successfully.");
                    yay.setContentText(
                            "You will now be directed to the login screen, please enter your email and password there to login.");
                    yay.showAndWait();
                    // LoginController li = new LoginController("Buyer");
                    App.setRoot("login");

                } else {
                    Alert warn = new Alert(AlertType.WARNING);
                    warn.setTitle("Error");
                    warn.setHeaderText("Signup unsuccessful");
                    warn.setHeaderText("An account with this information already exists. Please use new credentials, or go back to login");
                    warn.showAndWait();
                }
            } else if (result.get() == ButtonType.CANCEL) {
                Alert warn = new Alert(AlertType.WARNING);
                warn.setTitle("Error");
                warn.setHeaderText("Signup unsuccessful");
                warn.showAndWait();
            }
        }
    }

    @FXML
    void termsConditionsRead(ActionEvent event) {
        if (termsConditionsCheckBox.isSelected()) {
            Alert tnc = new Alert(AlertType.INFORMATION);
            tnc.setTitle("Terms and Conditions of TMMS");
            tnc.setHeaderText("Please read the following Terms and Conditions");
            tnc.setContentText(
                    "1. Personal use only \n2. Sell your soul to us 2 \n3. Condition the terms \n4. Term the conditions ");
            tnc.showAndWait();
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("welcomepg");
    }

}
