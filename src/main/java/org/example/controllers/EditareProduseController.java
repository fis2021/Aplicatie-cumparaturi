package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.exceptions.OutOfStockException;
import org.example.models.Produs;
import org.example.services.UserService;

import java.io.IOException;

public class EditareProduseController {
    @FXML
        private TextField idProdus;
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
        private TextArea ListaProduse;

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
            UserService.updateUser(App.getUser());
            App.setUser(null);
            UserService.updateDatabase();

        }

    public void salveaza() {

        Produs p;
        try {
            for (Produs prod : App.getUser().getProduse()) {
                if (idProdus.getText().equals(prod.getId())) {
                    prod.setDescriere(descriere.getText());
                    prod.setPret(Double.parseDouble(pret.getText()));
                    prod.setCantitate(Double.parseDouble(cantitate.getText()));
                    prod.setDenumire(denumire.getText());
                    UserService.updateUser(App.getUser());
                    mesaj.setText("Editare cu succes!");
                }
            }
        }catch(OutOfStockException e){
            mesaj.setText(e.getMessage());
        }
        initialize();

    }

    public void cautaProdus() {
        Produs p;
        //System.out.println(idProdus.getText());
        for(Produs prod:App.getUser().getProduse()){
            if(idProdus.getText().toString().equals(prod.getId())){
              //  System.out.println(idProdus.getText()+" "+prod.getId()+"\n");
                denumire.setText(prod.getDenumire());
                pret.setText(prod.getPret()+"");
                cantitate.setText(prod.getCantitate()+"");
                descriere.setText(prod.getDescriere());
            }
        }
    }
}



