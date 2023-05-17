package bytebazaarUI;

import bytebazaar.BusinessControllerFactory;
import bytebazaar.adminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AddFAQController {

    adminController adminC;

    public AddFAQController() {
        System.out.println("Add faq constructor");
    }

    public void set(adminController ac) {
        adminC = ac;
    }

    @FXML
    private TextArea answerfield;

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Button profileBtn;

    @FXML
    private TextArea quesfield;

    @FXML
    private Button uploadbtn;

    @FXML
    private Button wishlistBtn;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void goupload(ActionEvent event) {
        if (answerfield.getText().trim().equals("") || quesfield.getText().trim().equals("")) {
            Alert err = new Alert(AlertType.WARNING);
            err.setTitle("Error!");
            err.setHeaderText("Some required fields are empty");
            err.setContentText("Please make sure all fields are filled!");
            err.showAndWait();
        } else {
            BusinessControllerFactory.getAdminControllerInst().addFAQ(quesfield.getText(), answerfield.getText());

            Alert err = new Alert(AlertType.INFORMATION);
            err.setTitle("Uploaded!");
            err.setHeaderText("The new FAQ was uploaded!");
            err.showAndWait();
        }
    }

    @FXML
    void openCart(ActionEvent event) {

    }

}
