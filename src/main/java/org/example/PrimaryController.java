package org.example;

import java.io.File;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class PrimaryController {
    @FXML
    private ImageView cartView;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text message;

    public void initialize() {

        File cartFile =  new File("Images/cart.png");
        Image cartImage = new Image(cartFile.toURI().toString());
        cartView.setImage(cartImage);
    }
    @FXML
    private void handleLoginAction() throws IOException{
        String rol;
        try {
           rol= UserService.verifyCredentials(username.getText(), password.getText());
           // message.setText("Account created successfully!");
           if(rol == "Client") App.setRoot("HomePageClient.fxml");
           else App.setRoot("HomePageVanzator.fxml");
        } catch (InvalidCredentialsException e) {
            message.setText(e.getMessage());
        }

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary.fxml");
    }
}
