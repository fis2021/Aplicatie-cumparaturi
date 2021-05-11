package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.File;
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
        for(Produs prod: App.user.produse){
            s=s+prod.getId()+"  "+prod.getDenumire()+"  "+prod.getPret()+"  "+prod.getCantitate()+"  "+prod.getDescriere()+"\n";
        }
        ListaProduse.setText(s);
        idProdus.setText(App.user.getUsername()+"@");
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

        }

    public void salveaza() {

        Produs p;
        try {
            for (Produs prod : App.user.produse) {
                if (idProdus.getText().equals(prod.getId())) {
                    prod.setDescriere(descriere.getText());
                    prod.setPret(Double.parseDouble(pret.getText()));
                    prod.setCantitate(Integer.parseInt(cantitate.getText()));
                    prod.setDenumire(denumire.getText());
                    UserService.updateUser(App.user);
                    mesaj.setText("Editare cu succes!");
                }
            }
        }catch(OutOfStockException e){
            mesaj.setText(e.getMessage());
        }

    }

    public void cautaProdus() {
        Produs p;
        System.out.println(idProdus.getText());
        for(Produs prod:App.user.produse){
            if(idProdus.getText().toString().equals(prod.getId())){
                denumire.setText(prod.getDenumire());
                pret.setText(prod.getPret()+"");
                cantitate.setText(prod.getCantitate()+"");
                descriere.setText(prod.getDescriere());
            }
        }
    }
}



