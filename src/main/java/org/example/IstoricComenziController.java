package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class IstoricComenziController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
    }
}
