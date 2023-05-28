package bytebazaarUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import bytebazaar.App;
import bytebazaar.BusinessControllerFactory;
import bytebazaar.FAQ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FAQsUserController implements Initializable{
    int currentBuyerID;

    public void setData(int id) {
        this.currentBuyerID = id;
    }
    @FXML
    private Button backBtn1;

    @FXML
    private Button cartBtn1;

    @FXML
    private Label faqAnswer;

    @FXML
    private Label faqQuestion;

    @FXML
    private VBox faqsContainerVbox;

    @FXML
    private TextArea faqsearch;

    @FXML
    private Button profileBtn;

    @FXML
    private VBox singleFaqVbox;

    @FXML
    private Button wishlistBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/homepage.fxml"));
        HomepageController homepageCtrl = new HomepageController();
        homepageCtrl.setData(currentBuyerID);
        loader.setController(homepageCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        backBtn1.getScene().getWindow().hide();
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        cartBtn1.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/cart.fxml"));
        CartController cartCtrl = new CartController();
        cartCtrl.setData(currentBuyerID);
        loader.setController(cartCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setFAQs(LinkedList<FAQ> faqs) {
        faqsContainerVbox.getChildren().clear();

        for(int i=0;i<faqs.size();i++) {
            VBox singleFaqVbox = new VBox();
            singleFaqVbox.setMaxWidth(Double.POSITIVE_INFINITY);
            singleFaqVbox.setPrefHeight(100.0);
            singleFaqVbox.setPrefWidth(552.0);
            singleFaqVbox.setStyle("-fx-background-color: #C9D6DF; -fx-background-radius: 10;");

            Label faqQuestion = new Label(faqs.get(i).getQuestion());
            faqQuestion.setTextFill(Color.web("#002197"));
            faqQuestion.setFont(Font.font("System", FontWeight.BOLD, 14.0));

            Label faqAnswer = new Label(faqs.get(i).getAnswer());
            faqAnswer.setPrefHeight(91.0);
            faqAnswer.setPrefWidth(542.0);
            faqAnswer.setTextFill(Color.web("#002197"));
            faqAnswer.setWrapText(true);
            faqAnswer.setFont(Font.font(14.0));

            VBox.setMargin(singleFaqVbox, new Insets(10.0));
            VBox.setMargin(faqQuestion, new Insets(10.0, 0.0, 0.0, 0.0));
            VBox.setMargin(faqAnswer, new Insets(0.0, 0.0, 10.0, 0.0));

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(new Color(0, 0, 0, 0.38167938590049744));

            singleFaqVbox.getChildren().addAll(faqQuestion, faqAnswer);
            singleFaqVbox.setPadding(new Insets(10.0));
            singleFaqVbox.setEffect(dropShadow);

            faqsContainerVbox.getChildren().add(singleFaqVbox);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Following function will populate the faqLedger reference present in BuyerController and also
        //return the list of the faqs as populated.
        LinkedList<FAQ> faqs= BusinessControllerFactory.getBuyerControllerInst().getFAQs();

        faqsContainerVbox.getChildren().remove(singleFaqVbox);

        if(faqs!=null) {
            setFAQs(faqs);
        }
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        if(faqsearch.getText()==null || faqsearch.getText()=="") {
            return;
        }
        LinkedList<FAQ> faqs = BusinessControllerFactory.getBuyerControllerInst().findInFaq(faqsearch.getText());
        if(faqs==null) {
            Alert err = new Alert(AlertType.ERROR);
            err.setHeaderText("No results found");
            err.setContentText("Try a different keyword");
            err.showAndWait();
        } else {
            setFAQs(faqs);
        }
    }

    @FXML
    void resetButtonClicked(ActionEvent event) {
        LinkedList<FAQ> faqs=  BusinessControllerFactory.getBuyerControllerInst().refreshFAQs(); //Will load dynamically from db
        if(faqs!=null) {
            setFAQs(faqs);
        }
    }

}
