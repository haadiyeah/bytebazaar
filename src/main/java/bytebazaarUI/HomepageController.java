package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.DBHandler;
import bytebazaar.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomepageController implements Initializable{
    private LinkedList<Product> productsToDisplay; //will contain 9 products that are currently displayed

    @FXML
    private Button backBtn;

    @FXML
    private HBox buttonsHBox;

    @FXML
    private HBox buttonsHBox1;

    @FXML
    private HBox buttonsHBox11;

    @FXML
    private HBox buttonsHBox111;

    @FXML
    private HBox buttonsHBox1111;

    @FXML
    private HBox buttonsHBox11111;

    @FXML
    private HBox buttonsHBox11112;

    @FXML
    private HBox buttonsHBox11113;

    @FXML
    private HBox buttonsHBox11114;

    @FXML
    private Button cartBtn;

    @FXML
    private Button cartBtn1;

    @FXML
    private Button cartBtn2;

    @FXML
    private Button cartBtn3;

    @FXML
    private Button cartBtn4;

    @FXML
    private Button cartBtn5;

    @FXML
    private Button cartBtn6;

    @FXML
    private Button cartBtn7;

    @FXML
    private Button cartBtn8;

    @FXML
    private CheckBox category1;

    @FXML
    private CheckBox category2;

    @FXML
    private CheckBox category3;

    @FXML
    private CheckBox category4;

    @FXML
    private CheckBox category5;

    @FXML
    private CheckBox category6;

    @FXML
    private CheckBox category7;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button nextPageBtn;

    @FXML
    private ImageView productImage;

    @FXML
    private ImageView productImage1;

    @FXML
    private ImageView productImage2;

    @FXML
    private ImageView productImage3;

    @FXML
    private ImageView productImage4;

    @FXML
    private ImageView productImage5;

    @FXML
    private ImageView productImage6;

    @FXML
    private ImageView productImage7;

    @FXML
    private ImageView productImage8;

    @FXML
    private Label productPrice;

    @FXML
    private Label productPrice1;

    @FXML
    private Label productPrice2;

    @FXML
    private Label productPrice3;

    @FXML
    private Label productPrice4;

    @FXML
    private Label productPrice5;

    @FXML
    private Label productPrice6;

    @FXML
    private Label productPrice7;

    @FXML
    private Label productPrice8;

    @FXML
    private Label productTitle0;

    @FXML
    private Label productTitle1;

    @FXML
    private Label productTitle2;

    @FXML
    private Label productTitle3;

    @FXML
    private Label productTitle4;

    @FXML
    private Label productTitle5;

    @FXML
    private Label productTitle6;

    @FXML
    private Label productTitle7;

    @FXML
    private Label productTitle8;

    @FXML
    private Button profileBtn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchBtn;

    @FXML
    private VBox thumbnail;

    @FXML
    private VBox thumbnail1;

    @FXML
    private VBox thumbnail11;

    @FXML
    private VBox thumbnail111;

    @FXML
    private VBox thumbnail1111;

    @FXML
    private VBox thumbnail11111;

    @FXML
    private VBox thumbnail11112;

    @FXML
    private VBox thumbnail11113;

    @FXML
    private VBox thumbnail11114;

    @FXML
    private Button viewDetailBtn;

    @FXML
    private Button viewDetailBtn1;

    @FXML
    private Button viewDetailBtn2;

    @FXML
    private Button viewDetailBtn3;

    @FXML
    private Button viewDetailBtn4;

    @FXML
    private Button viewDetailBtn5;

    @FXML
    private Button viewDetailBtn6;

    @FXML
    private Button viewDetailBtn7;

    @FXML
    private Button viewDetailBtn8;

    @FXML
    private Button viewDetailbtn;

    @FXML
    private ComboBox<String> filtersComboBox;

    // @FXML
    // private ImageView wishlistBtn;

    LinkedList<Label> titleLabels;
    LinkedList<Label> priceLabels;
    LinkedList<Button> cartButtons;
    LinkedList<Button> viewButtons;
    LinkedList<ImageView> productImages;
    LinkedList <VBox> boxes;

    //productsToDisplay = getProductsToDisplay();

  

    @FXML
    void openCart(ActionEvent event) {
        
    }

    @FXML
    void searchProducts(ActionEvent event) throws IOException {
        if (searchBar.getText().equalsIgnoreCase("Vaio"))
            App.setRoot("noresults");
    }

    @FXML
    void openProfile(ActionEvent event) throws IOException {
        App.setRoot("viewingprofile");
    }

    @FXML
    void openProdDetail(ActionEvent event) throws IOException {
        App.setRoot("viewingproddetail");
    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void viewCompanyProfile(ActionEvent event) {

    }

    @FXML
    void viewReviews(ActionEvent event) {

    }

    void viewProducts(String filtername, LinkedList<String> categories) {

    }

    private LinkedList<Product> getProductsToDisplay() {
        // WRITE CODE TO GET A LIST OF PRODS
        LinkedList<Product> listProducts = new LinkedList<Product>();
        Product p = new Product(1, 190, "Laptop", 1, "https://icon-library.com/icon/icon-laptops-6.html", "Wow", 1);
        listProducts.add(p);

        Product p2 = new Product(2, 190, "Laptop2", 1, "https://icon-library.com/icon/icon-laptops-6.html", "Wow", 1);
        listProducts.add(p2);

        return listProducts;

    }

    @FXML
    void addToCart(ActionEvent event) {
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        System.out.println("Id pressed " + id);
    
    }


    @FXML
    void viewDetail(ActionEvent event) {
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        System.out.println("View Id pressed " + id);
    }

    //first 9 products passed will be set
    void setProducts(LinkedList<Product> products) {
        
        int i=0;
        int maxloops;
        if(products.size() <=9) {
            maxloops=products.size();
        } else {
            maxloops=9;
        }
        for(;i<maxloops;i++) {
            System.out.println("For loop i=" + i);
            productsToDisplay.add(products.get(i));
            if(products.get(i).getImageURL() !=null)
                productImages.get(i).imageProperty().set(new Image(products.get(i).getImageURL())); 
            else //default image
                productImages.get(i).imageProperty().set(new Image("https://icon-library.com/images/img-icon/img-icon-0.jpg")); 
            titleLabels.get(i).setText(products.get(i).getName());
            priceLabels.get(i).setText("Rs. " + products.get(i).getPrice() + "/-");
        }
        //remaining boxes are hidden
        while(i<9){
            System.out.println("While Loop i=" + i);
            boxes.get(i).setVisible(false);
            i++;
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Top Selling", "Price - Low to High", "Price - High to Low", "Name - A-Z", "Name - Z-A");
        
        filtersComboBox= new ComboBox<String>();
        filtersComboBox.setItems(options);titleLabels=new LinkedList<Label>();
        priceLabels=new LinkedList<Label>();
        productImages = new LinkedList<ImageView>();
        cartButtons=new LinkedList<Button>();
        viewButtons=new LinkedList<Button>();
        boxes=new LinkedList<VBox>();
        productsToDisplay=new LinkedList<Product>();
       
       titleLabels.add(productTitle0);
       titleLabels.add(productTitle1);
       titleLabels.add(productTitle2);
       titleLabels.add(productTitle3);
       titleLabels.add(productTitle4);
       titleLabels.add(productTitle5);
       titleLabels.add(productTitle6);
       titleLabels.add(productTitle7);
       titleLabels.add(productTitle8);

       priceLabels.add(productPrice);
       priceLabels.add(productPrice1);
       priceLabels.add(productPrice2);
       priceLabels.add(productPrice3);
       priceLabels.add(productPrice4);
       priceLabels.add(productPrice5);
       priceLabels.add(productPrice6);
       priceLabels.add(productPrice7);
       priceLabels.add(productPrice8);

       cartButtons.add(cartBtn);
       cartButtons.add(cartBtn1);
       cartButtons.add(cartBtn2);
       cartButtons.add(cartBtn3);
       cartButtons.add(cartBtn4);
       cartButtons.add(cartBtn5);
       cartButtons.add(cartBtn6);
       cartButtons.add(cartBtn7);
       cartButtons.add(cartBtn8);

       viewButtons.add(viewDetailBtn);
       viewButtons.add(viewDetailBtn1);
       viewButtons.add(viewDetailBtn2);
       viewButtons.add(viewDetailBtn3);
       viewButtons.add(viewDetailBtn4);
       viewButtons.add(viewDetailBtn5);
       viewButtons.add(viewDetailBtn6);
       viewButtons.add(viewDetailBtn7);
       viewButtons.add(viewDetailBtn8);

       productImages.add(productImage);
       productImages.add(productImage1);
       productImages.add(productImage2);
       productImages.add(productImage3);
       productImages.add(productImage4);
       productImages.add(productImage5);
       productImages.add(productImage6);
       productImages.add(productImage7);
       productImages.add(productImage8);

       boxes.add(thumbnail);
       boxes.add(thumbnail1);
       boxes.add(thumbnail11);
       boxes.add(thumbnail111);
       boxes.add(thumbnail1111);
       boxes.add(thumbnail11111);
       boxes.add(thumbnail11112);
       boxes.add(thumbnail11113);
       boxes.add(thumbnail11114);

       for(int i=0;i<9;i++) {
           viewButtons.get(i).setId("view-"+i);
           cartButtons.get(i).setId("cart-"+i);
           boxes.get(i).setId("box-"+i);
       }

       

       productsToDisplay = BusinessControllerFactory.getBuyerControllerInst().getTopProducts();

       if(productsToDisplay!=null) {
        System.out.println("Productstodisplay not null, setting");
        setProducts(productsToDisplay);
       } else {
        System.out.println("null");
       }
    }

    @FXML
    void browseProductsClicked(ActionEvent event) {

    }






}
