package bytebazaar;

import bytebazaarUI.*;
//import bytebazaarUI.*;

import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene scene;
    // jcnwjncjan

    @Override
    public void start(Stage stage) throws IOException {
        LinkedList<Buyer> test=new LinkedList<Buyer>();
        test.add(new Buyer("aaa","ss","ss","dd"));
        test.add(new Buyer("aeea","ss","ss","dd"));
        test.forEach(text-> {
            System.out.println(text.getEmail());
        });
        System.out.println("-------------");
        test.get(0).setEmail("poopoo");
        test.forEach(text-> {
            System.out.println(text.getEmail());
        });

        scene = new Scene(loadFXML("welcomepg"), 600, 430);
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
        // System.out.println("JavaFX Version: " +
        // System.getProperty("javafx.version"));
        // System.out.println("JavaFX Runtime Version: " +
        // System.getProperty("javafx.runtime.version"));
        launch();
    }
}
