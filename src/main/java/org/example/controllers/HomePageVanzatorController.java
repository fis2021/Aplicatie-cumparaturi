package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.App;

import java.io.File;
import java.io.IOException;

public class HomePageVanzatorController {
    @FXML
    private ImageView FundalVanzator;

    @FXML
    public void initialize() {

        File fundalFile =  new File("Images/fundal_vanzator.jpg");
        Image fundalImage = new Image(fundalFile.toURI().toString());
        FundalVanzator.setImage(fundalImage);
    }

    @FXML
    private void SwitchToEditareProduse() throws IOException {
        App.setRoot("EditareProduse.fxml");
    }

    @FXML
    private void SwitchToStergereProduse() throws IOException {
        App.setRoot("StergereProduse.fxml");
    }

    @FXML
    private void SwitchToAdaugareProduse() throws IOException {
        App.setRoot("AdaugareProduse.fxml");
    }

    @FXML
    private void SwitchToComenzi() throws IOException {
        App.setRoot("Comenzi.fxml");
    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
        App.setUser(null);
    }
}
