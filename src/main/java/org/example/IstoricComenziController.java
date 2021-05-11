package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class IstoricComenziController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
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
