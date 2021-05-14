package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.example.App;
import org.example.services.UserService;
import org.example.models.Produs;

import java.io.IOException;

public class StergereProduseController {

    @FXML
    private TextField idProdus;

    @FXML
    private TextArea ListaProduse;

    @FXML
    private Text mesaj1;

    @FXML
    private Text mesaj2;

    private Produs produs;

    @FXML
    public void initialize() {
        String s;
        s="Id      Denumire      Pret    Cantitate   Descriere\n";
        for(Produs prod: App.getUser().getProduse()){
            s=s+prod.getId()+"  "+prod.getDenumire()+"  "+prod.getPret()+"  "+prod.getCantitate()+"  "+prod.getDescriere()+"\n";
        }
        ListaProduse.setText(s);
        idProdus.setText(App.getUser().getUsername()+"@");
    }

    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageVanzator.fxml");
    }
    @FXML
    private void SwitchToEditareProduse() throws IOException {
        App.setRoot("EditareProduse.fxml");
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
        UserService.updateUser(App.getUser());
        App.setUser(null);
        UserService.updateDatabase();
    }

    public void stergereProdus() {
        int index;
        index=App.getUser().getProduse().indexOf(produs);
        App.getUser().getProduse().remove(index);
        UserService.updateUser(App.getUser());
        mesaj2.setText("Produs sters cu succes!");
        initialize();
    }

    public void cautaProdus() {
        Produs p;
        String s="Id incorect!";
        for(Produs prod:App.getUser().getProduse()){
            if(idProdus.getText().toString().equals(prod.getId())){
                produs=prod;
               s="Produs gasit!";
            }
        }
        mesaj1.setText(s);
    }
}
