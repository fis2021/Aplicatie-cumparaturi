package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class HomePageClientController {
    @FXML
    private ImageView FundalClient;
    @FXML
    public void initialize() {

        File fundalFile =  new File("Images/fundal_client.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        FundalClient.setImage(fundalImage);
    }
    @FXML
    private void SwitchToIstoric() throws IOException {
        App.setRoot("IstoricComenzi.fxml");
    }
    @FXML
    private void SwitchToCos() throws IOException {
        App.setRoot("CosCumparaturi.fxml");
    }

    @FXML
    private void SwitchToProduse() throws IOException {
        App.setRoot("VizProduse.fxml");
    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
        App.setUser(null);
    }

}

