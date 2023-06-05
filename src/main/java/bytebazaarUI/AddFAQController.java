package bytebazaarUI;

import java.io.IOException;
import java.net.URL;

import bytebazaar.BusinessControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddFAQController {

    public AddFAQController() {
        System.out.println("Add faq constructor");
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
    void goBack(ActionEvent event) throws IOException {
        //Redirecting to the admin dashboard page.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/admindashboard.fxml"));
        AdminDashboardController adminDashboardCtrl = new AdminDashboardController();
        loader.setController(adminDashboardCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("ByteBazaar - the hardware Solution");
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
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
            if (
            BusinessControllerManager.getAdminControllerInst().addFAQ(quesfield.getText(), answerfield.getText())
            ) {
                Alert err = new Alert(AlertType.INFORMATION);
                err.setTitle("Uploaded!");
                err.setHeaderText("The new FAQ was uploaded!");
                err.showAndWait();
            } else {
                Alert err = new Alert(AlertType.WARNING);
                err.setTitle("Error!");
                err.setHeaderText("FAQ not uploaded");
                err.setContentText("Please make sure it's not a duplicate FAQ");
                err.showAndWait();
            }
        }
    }

    @FXML
    void openCart(ActionEvent event) {
        //do nothing u are admin no shopping for u
    }

}
