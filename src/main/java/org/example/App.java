package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static String PATH_CSS = "css/";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("startView"), 640, 480);
        scene.getStylesheets().add(loadCSS("startView"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        String css = loadCSS(fxml);
        if(css != null)
            scene.getStylesheets().add(loadCSS(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static String loadCSS (String cssFile) {
        URL cssURL = App.class.getResource(PATH_CSS+cssFile+".css");
        if (cssURL != null)
            return cssURL.toExternalForm();
        else
            return null;
    }


    public static void main(String[] args) {
        launch();
    }

}