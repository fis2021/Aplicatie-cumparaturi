package org.example.controllers;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import org.example.App;
import org.example.services.UserService;
import org.example.exceptions.UsernameAlreadyExistsException;

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

       // rol.getItems().addAll("Client","Vanzator");
      //  rol.getItems().addAll("CLient","Vanzator");


    @FXML
    public void initialize() {
        rol.getItems().addAll("Client","Vanzator");

        File lockFile =  new File("Images/padlock_PNG9428.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockView.setImage(lockImage);
    }

    @FXML
    public void handleRegisterAction() {
        if (usernameField.getText() != null && passwordField.getText() != null && rol.getValue() != null) {
            try {
                UserService.addUser(usernameField.getText(), passwordField.getText(), (String) rol.getValue());
                message.setText("Account created successfully!");
            } catch (UsernameAlreadyExistsException e) {
                message.setText(e.getMessage());
            }
        }
        else message.setText("Completati toate campurile!");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary.fxml");
    }
}