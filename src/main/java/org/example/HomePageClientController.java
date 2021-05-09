package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class HomePageClientController {
    @FXML
    private ImageView fundal;
    @FXML
    public void initialize() {

        File fundalFile =  new File("Images/fundal_client.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        fundal.setImage(fundalImage);
    }
}

