package bytebazaarUI;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bytebazaar.BusinessControllerFactory;
import bytebazaar.SellerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewProductController implements Initializable {
    private int currentSellerID;

    public void setData(int sellerID) {
        this.currentSellerID = sellerID;
    }


    @FXML
    private Button backBtn;

    @FXML
    private ComboBox newProductCategory;

    @FXML
    private TextArea newProductDesc;

    @FXML
    private TextField newProductImageURL;

    @FXML
    private TextField newProductName;

    @FXML
    private TextField newProductQuantity;

    @FXML
    private TextField newProductPrice;

    @FXML
    private Label productName;

    @FXML
    private Button profileButton;

    @FXML
    void addProduct(ActionEvent event) {
        String errorMessage="";
        try {
            Integer.parseInt(newProductQuantity.getText());
        } catch (NumberFormatException nfe) {
            errorMessage += "Invalid product quantity\n";
        }
        try {
            Float.parseFloat(newProductPrice.getText());
            if(Float.parseFloat(newProductPrice.getText()) < 100) {
                errorMessage += "Minimum price is 100\n";
            }
        } catch (NumberFormatException nfe) {
            errorMessage += "Invalid price\n";
        }
        
        if (newProductName.getText().trim().equals("") || newProductPrice.getText().trim().equals("")
                || newProductQuantity.getText().trim().equals("")
                || newProductImageURL.getText().trim().equals("") || newProductDesc.getText().trim().equals("")
                || ((String) newProductCategory.getValue() == null))
        {    
            errorMessage += "Some required fields are empty\n";
        }

        if (errorMessage != "") {
            Alert err = new Alert(AlertType.WARNING);
            err.setTitle("Error!");
            err.setHeaderText("Please resolve the following");
            err.setContentText(errorMessage);
            err.showAndWait();
            return;
        }

        //If you are here, the checks have been cleared.
        if (BusinessControllerFactory.getSellerControllerInst().addNewProduct(currentSellerID, newProductName.getText(),
                Float.parseFloat(newProductPrice.getText()), Integer.parseInt(newProductQuantity.getText()),
                newProductImageURL.getText(),
                newProductDesc.getText(), (String) newProductCategory.getValue())) {
            Alert yay = new Alert(AlertType.INFORMATION);
            yay.setTitle("Product added");
            yay.setHeaderText("The product was added successfully");
            yay.setContentText("Congratulations on adding a new product. Happy selling!");
            yay.showAndWait();
        } else {
            Alert err = new Alert(AlertType.WARNING);
            err.setTitle("Error!");
            err.setHeaderText("Product not added");
            err.setContentText("An error occurred.");
            err.showAndWait();
        }

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Alert err = new Alert(AlertType.WARNING);
        err.setTitle("Warnng");
        err.setHeaderText("Changes not saved");
        err.setContentText("You are being redirected");
        err.showAndWait();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/sellerdashboard.fxml"));
        SellerDashboardController sellerDashboardCtrl = new SellerDashboardController();
        sellerDashboardCtrl.setData(currentSellerID);
        loader.setController(sellerDashboardCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Alert err = new Alert(AlertType.WARNING);
        err.setTitle("Warnng");
        err.setHeaderText("Changes not saved");
        err.setContentText("You are being redirected");
        err.showAndWait();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/sellerdashboard.fxml"));
        SellerDashboardController sellerDashboardCtrl = new SellerDashboardController();
        sellerDashboardCtrl.setData(currentSellerID);
        loader.setController(sellerDashboardCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         // Setting the combo box
         //newProductCategory = new ComboBox<String>();
         ObservableList<String> options = FXCollections.observableArrayList();
         options.addAll("Keyboards", "Mice", "Monitors", "Graphic Cards", "Controllers", "Laptops", "PCs");
         newProductCategory.setItems(options);// Ignore the warning
    }

}
