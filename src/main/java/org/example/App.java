package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Produs;
import org.example.User;
import org.example.ComandaService;
import org.example.FileSystemService;
import org.example.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    private static User user;
    private static ArrayList<Produs> cosCumparaturi;
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        initDirectory();
        UserService.initDatabase();
        ComandaService.initDatabase();
        scene = new Scene(loadFXML("primary.fxml"), 490, 420);
        stage.setScene(scene);
        stage.show();
    }

    public static User getUser(){
        return user;
    }

    public static ArrayList<Produs> getCos(){
        return cosCumparaturi;
    }

    public static void setUser(User user){
        App.user=user;
    }
    public static void addCos(Produs p){
        cosCumparaturi.add(p);
    }
    public static void golireCos(){
        cosCumparaturi=null;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

    public static void main(String[] args) {
        cosCumparaturi= new ArrayList<Produs>();
        launch();
    }

}