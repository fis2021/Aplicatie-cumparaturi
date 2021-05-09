package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class StergereProduseController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageVanzator.fxml");
    }
}
