package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class HomePageVanzatorController { {
    @FXML
    private ImageView FundalVanzator;
    @FXML
    public void initialize() {

        File fundalFile =  new File("Images/fundal_vanzator.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        FundalVanzator.setImage(fundalImage);
    }

    @FXML
    private void SwitchToIstoric() throws IOException {
        App.setRoot("AdaugareProduse.fxml");
    }

    @FXML
    private void SwitchToCos() throws IOException {
        App.setRoot("EditareProduse.fxml");
    }

    @FXML
    private void SwitchToProduse() throws IOException {
        App.setRoot("StergereProduse.fxml");
    }
    @FXML
    private void SwitchToProduse() throws IOException {
        App.setRoot("Comenzi.fxml");
    }
}