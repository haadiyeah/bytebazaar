package bytebazaar;

import bytebazaarUI.*;

import java.io.IOException;
import java.net.URL;

import bytebazaarUI.WelcomePgController;
import bytebazaarUI.ViewingOrdersBuyerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene scene;
    //aa

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/bytebazaar/viewingordersbuyer.fxml"));
        // WelcomePgController welcomePgCtrl = new WelcomePgController();
        ViewingOrdersBuyerController welcomePgCtrl = new ViewingOrdersBuyerController();
        welcomePgCtrl.setData(2);
        loader.setController(welcomePgCtrl);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("ByteBazaar - the hardware solution");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
