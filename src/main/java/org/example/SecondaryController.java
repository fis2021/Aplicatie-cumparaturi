package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;

public class SecondaryController {
    @FXML
    private ComboBox rol;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text message;
    @FXML
    private ImageView lockView;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        rol.getItems().addAll("Client", "Vanzator");
        File lockFile =  new File("Images/padlock_PNG9428.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockView.setImage(lockImage);
    }

    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) rol.getValue());
            message.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            message.setText(e.getMessage());
        }
    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary.fxml");
    }
}