package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


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
        User user;

        try {
           user = UserService.verifyCredentials(username.getText(), password.getText());
           // message.setText("Account created successfully!");
           if(user.getRole().equals(new String("Client"))) {
               App.setRoot("HomePageClient.fxml");
               App.setUser(user);
           }
           else {
               App.setRoot("HomePageVanzator.fxml");
               App.setUser(user);
           }
        } catch (InvalidCredentialsException e) {
            message.setText(e.getMessage());
        }

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary.fxml");
    }
}
