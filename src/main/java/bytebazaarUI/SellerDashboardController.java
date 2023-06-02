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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SellerDashboardController {
    private int currentSellerID;

    public void setData(int sellerID) {
        this.currentSellerID = sellerID;
    }

    @FXML
    private Button backBtn;

    @FXML
    private Label productName;

    @FXML
    void addNewProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/addnewproduct.fxml"));
        AddNewProductController addNewProductCtrl = new AddNewProductController();
        addNewProductCtrl.setData(currentSellerID);
        loader.setController(addNewProductCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("ByteBazaar - the hardware Solution");
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Exit?");
        confirm.setHeaderText("Do you want to exit? You will be logged out");

        Optional<ButtonType> result = confirm.showAndWait();
         if (result.get() == ButtonType.OK) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Logout successful");
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
            backBtn.getScene().getWindow().hide();
        }

    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @FXML
    void viewAllOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingordersseller.fxml"));
            ViewingOrdersSellerController viewingOrdersCtrl = new ViewingOrdersSellerController();
            viewingOrdersCtrl.setData(currentSellerID);
            loader.setController(viewingOrdersCtrl);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            backBtn.getScene().getWindow().hide();

    }

    @FXML
    void viewYourProducts(ActionEvent event) {

    }



}
