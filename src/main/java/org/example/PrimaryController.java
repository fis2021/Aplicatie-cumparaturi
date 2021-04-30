package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.App;

public class PrimaryController {
    @FXML
    private ImageView cartView;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        File cartFile =  new File("Images/cart.png");
        Image cartImage = new Image("Images/cart.png");
        cartView.setImage(cartImage);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary.fxml");
    }
}
