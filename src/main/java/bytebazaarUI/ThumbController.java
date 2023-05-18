package bytebazaarUI;
import bytebazaar.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ThumbController {

    @FXML
    private HBox buttonsHBox;

    @FXML
    private Button cartBtn;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productPrice;

    @FXML
    private Label productTitle;

    @FXML
    private VBox thumbnail;

    @FXML
    private Button viewDetailBtn;

    public void setData(Product p) {
        Image img = new Image(getClass().getResourceAsStream(p.getImageURL()));
        productImage.setImage(img);

        productPrice.setText("Rs. " + p.getPrice() + "/-");
        productTitle.setText(p.getName());

        
    }

}
