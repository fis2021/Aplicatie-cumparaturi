package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VizProduseController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
    }
}
