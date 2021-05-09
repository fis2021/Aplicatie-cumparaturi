package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class CosCumparaturiController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
    }
}
