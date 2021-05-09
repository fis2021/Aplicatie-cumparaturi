package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class ComenziController {
    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageVanzator.fxml");
    }
}
