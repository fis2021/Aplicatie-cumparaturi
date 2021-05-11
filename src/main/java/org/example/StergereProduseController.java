package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

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
    }

    public void stergereProdus() {
        int index;
        index=App.user.produse.indexOf(produs);
        App.user.produse.remove(index);
        UserService.updateUser(App.user);
        mesaj2.setText("Produs sters cu succes!");
    }

    public void cautaProdus() {
        Produs p;
        String s="Id incorect!";
        for(Produs prod:App.user.produse){
            if(idProdus.getText().toString().equals(prod.getId())){
                produs=prod;
               s="Produs gasit!";
            }
        }
        mesaj1.setText(s);
    }
}
