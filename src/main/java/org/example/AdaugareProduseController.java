package org.example;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class AdaugareProduseController {
    @FXML
    private TextField denumire;
    @FXML
    private TextField pret;
    @FXML
    private TextField cantitate;
    @FXML
    private TextArea descriere;
    @FXML
    private Text mesaj;


    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageVanzator.fxml");
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
    private void SwitchToComenzi() throws IOException {
        App.setRoot("Comenzi.fxml");
    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
    }

    @FXML
    private void addProduct(){
        Produs p;
        p=new Produs(Double.parseDouble(pret.getText()),Integer.parseInt(cantitate.getText()),descriere.getText(), denumire.getText());
        App.user.addProduse(p);
        UserService.updateUser(App.user);
        denumire.setText("");
        pret.setText("");
        cantitate.setText("");
        descriere.setText("");
        mesaj.setText("Produsul a fost adaugat cu succes!");
    }
}
