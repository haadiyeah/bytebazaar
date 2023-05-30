package bytebazaarUI;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class SelectViewController {

    @FXML
    private Button buyerBtn;

    @FXML
    private Button sellerBtn;

    @FXML
    private StackPane welcomeSideBox;

    @FXML
    //user will come to SELECT VIEW from LOGIN.
    //After login it will ask which view to open. (It is buyer by default)
    void openBuyerHomepage(ActionEvent event) throws IOException{
        //LoginController lc = new LoginController("Buyer");
    }

    @FXML
    void openSellerHomepage(ActionEvent event) {

    }

}
