package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomepageController implements Initializable {
    private LinkedList<Product> productsToDisplay; // will contain 9 products that are currently displayed

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
    private Button backPageButton;

    @FXML
    private Label titleLabel;

    // @FXML
    // private ComboBox<String> filtersComboBox;

    @FXML
    private ComboBox filtersComboBox; //ignore warning

    // @FXML
    // private ImageView wishlistBtn;

    LinkedList<Label> titleLabels;
    LinkedList<Label> priceLabels;
    LinkedList<Button> cartButtons;
    LinkedList<Button> viewButtons;
    LinkedList<ImageView> productImages;
    LinkedList<VBox> boxes;
    LinkedList<CheckBox> checkboxes;

    // productsToDisplay = getProductsToDisplay();

    @FXML
    //Cart button clicked
    void openCart(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    //Search products button clicked
    void searchProducts(ActionEvent event) throws IOException {
        //Error handling
        String searched = searchBar.getText();
        if(searched.isBlank())
        return;

        //Searching for the products in the existing list
       LinkedList<Product> resultsToDisplay= new LinkedList<Product>();
        productsToDisplay.forEach(product -> {
            if(product.getName().toLowerCase().contains(searched.toLowerCase())) {
                resultsToDisplay.add(product);
            }
        });

        if(resultsToDisplay.isEmpty()){
            titleLabel.setText("No results!");
        }
        tracker=0;
        setProducts(resultsToDisplay);
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
    //Back button will cause user to logout
    void goBack(ActionEvent event) throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Exit?");
        confirm.setHeaderText("Do you want to exit? You will be logged out");
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
    void openFAQs(ActionEvent event) throws IOException {
        App.setRoot("faqsuser");
    }

    @FXML
    //Adding to cart directly from browse products page
    void addToCart(ActionEvent event) {
        //Checking which button was clicked
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        System.out.println("Add to cart clicked "+id);

        //Adding to cart through buyer controller
        BusinessControllerFactory.getBuyerControllerInst().addToCart(productsToDisplay.get(id)); 
       
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Added to cart successfully");
        alert.setHeaderText("You have added to cart");
        alert.showAndWait();

    }

    @FXML
    //Viewing detail of a particular product
    void viewDetail(ActionEvent event) throws IOException {
        //Checking which button was clicked
        Button numberButton = (Button) event.getTarget();
        int id = Integer.parseInt(numberButton.getId().split("-")[1]);
        if(tracker>9 && tracker<18) {
            id+=9;
        }//TODO Add ceiling function
        BusinessControllerFactory.getBuyerControllerInst().setCurrentProduct(productsToDisplay.get(id));
       System.out.println("View Id pressed " + id);
       App.setRoot("viewingproddetail");
    }

    static int tracker;

    // first 9 products passed will be set
    void setProducts(LinkedList<Product> products) {
        
        int i =tracker;
        int maxloops;
        if (products.size() <= 9) {
            maxloops = products.size();
        } else {
            maxloops = 9;
        }
        int j;
        for (j=0; j < maxloops && i<products.size(); j++, i++) {
            System.out.println("For loop i=" + i +" j = " +j);
           // productsToDisplay.add(products.get(i));
            tracker++;
            System.out.println("Tracker = " + tracker);
            if (products.get(i).getImageURL() != null)
                productImages.get(j).imageProperty().set(new Image(products.get(i).getImageURL()));
            else // default image
                productImages.get(j).imageProperty()
                        .set(new Image("https://icon-library.com/images/img-icon/img-icon-0.jpg"));
            titleLabels.get(j).setText(products.get(i).getName());
            priceLabels.get(j).setText("Rs. " + products.get(i).getPrice() + "/-");
        }
        // remaining boxes are hidden
        while (j < 9) {
            System.out.println("While Loop i=" + i);
            boxes.get(j).setVisible(false);
            j++;
        }
    }

    @FXML
    void browseProductsClicked(ActionEvent event) {
        String selectedFilter = (String) filtersComboBox.getValue();
        if(selectedFilter==null) {
            selectedFilter="Top Selling";//default
        }
        System.out.println("Filter = " + selectedFilter);
        LinkedList<String> selectedCategories = new LinkedList<String>();
        
        checkboxes.forEach(checkbox -> {
            if (checkbox.isSelected()) {
                selectedCategories.add(checkbox.getText());
            }
        });

        
        productsToDisplay = BusinessControllerFactory.getBuyerControllerInst().getProducts(selectedFilter, selectedCategories);
        if (productsToDisplay != null) {
            System.out.println("Productstodisplay not null, setting");
            tracker=0;//When setting from db tracker=0 but when next page it will not be 0
            setProducts(productsToDisplay);
        }
    }

    @FXML
    void openNextPage(ActionEvent event) {
        System.out.println("Next button clicked-------------\ntrakcer="+tracker);
        productsToDisplay.forEach(product -> { System.out.println(product.getName());});
        if(productsToDisplay.size()>9)
         setProducts(productsToDisplay); //without making tracker=0 so it will continue where it left off
    }

    @FXML
    void openBackPage(ActionEvent event) throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Exit?");
        confirm.setHeaderText("Do you want to exit? You will be logged out");
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // filtersComboBox= new ComboBox<String>();

        //Setting the filter combo box
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Top Selling", "Price - Low to High", "Price - High to Low", "Name - A-Z", "Name - Z-A");
        filtersComboBox.setItems(options);//Ignore the warning

        //Setting the current user, as this screen will be displaying 
        //right adfter buyer logs in
        BusinessControllerFactory.getBuyerControllerInst().setCurrentUser(BusinessControllerFactory.getLoginControllerInst().getCurrentUser());
        
        //Initlalizing the lists to store information for product browsing
        titleLabels = new LinkedList<Label>();
        priceLabels = new LinkedList<Label>();
        productImages = new LinkedList<ImageView>();
        cartButtons = new LinkedList<Button>();
        viewButtons = new LinkedList<Button>();
        boxes = new LinkedList<VBox>();
        checkboxes = new LinkedList<CheckBox>();
        productsToDisplay = new LinkedList<Product>();

        //Populating the lists
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

        checkboxes.add(category1);
        checkboxes.add(category2);
        checkboxes.add(category3);
        checkboxes.add(category4);
        checkboxes.add(category5);
        checkboxes.add(category6);
        checkboxes.add(category7);

        //Setting IDs which will need to be accessed later to manipulate the grid
        for (int i = 0; i < 9; i++) {
            viewButtons.get(i).setId("view-" + i);
            cartButtons.get(i).setId("cart-" + i);
            boxes.get(i).setId("box-" + i);
        }
        LinkedList<String> selectedCategories = new LinkedList<String>();
        
        //Seeing which categories are selected
        checkboxes.forEach(checkbox -> {
            if (checkbox.isSelected()) {
                selectedCategories.add(checkbox.getText());
            }
        });

        //Getting the products to display with the default filter and selected categories
        productsToDisplay = BusinessControllerFactory.getBuyerControllerInst().getProducts("Top Selling", selectedCategories);

        //Displaying the products
        if (productsToDisplay != null) {
            System.out.println("Productstodisplay not null, setting");
            tracker=0;
            setProducts(productsToDisplay);
        } else {
            System.out.println("null");
        }
    }

}
