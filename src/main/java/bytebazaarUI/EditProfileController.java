package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Buyer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditProfileController implements Initializable {
    @FXML
    private Button deleteAccButton;

    @FXML
    private Button logoutButton;

    // @FXML
    // private ImageView wishlistBtn;

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
    private Button cartBtn;

    @FXML
    private Button profileBtn;

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
    // Alert confirm = new Alert(AlertType.WARNING);
    // confirm.setTitle("Warning!");
    // confirm.setHeaderText("Are you sure you want to delete your account?");
    // confirm.setContentText("This is a permanent action and cannot be undone!");
    // confirm.showAndWait();
    // }

    @FXML
    void deleteAccount(ActionEvent event) throws IOException {
        Alert confirm = new Alert(AlertType.WARNING);
        confirm.setTitle("Delete account?");
        confirm.setHeaderText("Do you really want to delete your account?");
        confirm.setContentText("All your data will be permanently removed. This cannot be undone. Are you sure?");
        confirm.showAndWait();

        Optional<ButtonType> result = confirm.showAndWait();
        if (!result.isPresent() || result.get() == ButtonType.CANCEL) {
            // Not logged out, show message?
        } else if (result.get() == ButtonType.OK) {
            if (BusinessControllerFactory.getBuyerControllerInst()
                    .deleteBuyer()) { //will automatically select current User to delete. 
                
                BusinessControllerFactory.getLoginControllerInst().logout();
                Alert yay = new Alert(AlertType.INFORMATION);
                yay.setTitle("Account deleted");
                yay.setHeaderText("Your account has been deleted");
                yay.setContentText("You will be redirected shortly");
                yay.showAndWait();
                App.setRoot("welcomepg");
            } else {
                Alert ohno = new Alert(AlertType.ERROR);
                ohno.setTitle("Error");
                ohno.setHeaderText("There was an error in deleting your account");
                ohno.setContentText("Sorry, your account was not deleted properly");
                ohno.showAndWait();
            }

        }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
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

        // checking the phone number ; making it a required field and enforcing digits
        // only
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
        // Successful verification, display confirmation
        else {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Profile changes!");
            confirm.setHeaderText("Do you want to save the changes?");
            confirm.showAndWait();

            Optional<ButtonType> result = confirm.showAndWait();
            if (!result.isPresent()) {
                // alert is exited, no button has been pressed.
            } else if (result.get() == ButtonType.OK) {
                // Save changes
                String newName = nameText.getText();
                String newPhone = phoneText.getText();
                String newEmail = emailText.getText();
                String newDeliveryDeets = addressText.getText();
                String newPassword = passwordText.getText();

                if (BusinessControllerFactory.getBuyerControllerInst().updateCurrentUser(newName, newEmail, newPassword,
                        newPhone, newDeliveryDeets)) {
                    Alert yay = new Alert(AlertType.INFORMATION);
                    yay.setTitle("Profile changes!");
                    yay.setHeaderText("The changes were made successfully");
                    yay.showAndWait();
                }
                App.setRoot("viewingprofile");
            } else if (result.get() == ButtonType.CANCEL) {
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
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    @FXML
    void openWishlist(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Buyer currentBuyer = (Buyer) BusinessControllerFactory.getBuyerControllerInst().getCurrentUser();
        nameText.setText(currentBuyer.getName());
        emailText.setText(currentBuyer.getEmail());
        phoneText.setText(currentBuyer.getPhoneNum());
        addressText.setText(currentBuyer.getDeliveryDetails());
        passwordText.setText(currentBuyer.getPassword());
    }

}
