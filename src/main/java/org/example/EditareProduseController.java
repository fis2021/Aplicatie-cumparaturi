package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class EditareProduseController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageVanzator.fxml");
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

        }
    }



