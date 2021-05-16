package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.EventListener;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.App;
import org.example.models.Produs;
import org.example.services.UserService;

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
        UserService.updateUser(App.getUser());
        App.setUser(null);
        UserService.updateDatabase();
    }

    @FXML
    private void addProduct(){
        Produs p;
        if(pret.getText() != null && pret.getText().trim().isEmpty()==false &&cantitate.getText() != null && cantitate.getText().trim().isEmpty()==false && descriere.getText() != null && descriere.getText().trim().isEmpty()==false && denumire.getText() != null && denumire.getText().trim().isEmpty()==false)
        {
            p=new Produs(Double.parseDouble(pret.getText()),Double.parseDouble(cantitate.getText()),descriere.getText(), denumire.getText());
            App.getUser().addProduse(p);
            UserService.updateUser(App.getUser());
            System.out.println(p.toString());
            denumire.setText("");
            pret.setText("");
            cantitate.setText("");
            descriere.setText("");
            mesaj.setText("Produsul a fost adaugat cu succes!");
        }
        else mesaj.setText("Completati toate campurile!");
    }
}
