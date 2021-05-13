package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.models.Produs;
import org.example.models.User;
import org.example.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class VizProduseController {

    @FXML
        private TextField idProdus;
    @FXML
        private TextField cantitate;
    @FXML
        private Text mesaj;
    @FXML
        private Text total;
    @FXML
        private Text mesajCauta;
    @FXML
        private TextArea ListaProduse;
    private static  ArrayList<User> vanzatori= UserService.getVanzatori();
    private static Produs p;

    @FXML
    private void initialize(){

        String s="Id produs    Denumire    Cantitate Disponibila   Pret   Descriere \n";
        for(User user: vanzatori){
            s=s+" Produsele oferite de :"+user.getUsername()+"\n";
            for(Produs prod:user.getProduse()){
                s=s+prod.getId()+"   "+prod.getDenumire()+"   "+prod.getCantitate()+"  "+prod.getPret()+" lei    " + prod.getDescriere()+"\n";
            }
            s=s+"\n";
        }
        ListaProduse.setText(s);
    }

    @FXML
    private void SwitchToIstoric() throws IOException {
        App.setRoot("IstoricComenzi.fxml");
    }

    @FXML
    private void SwitchToCos() throws IOException {
        App.setRoot("CosCumparaturi.fxml");
    }

    @FXML
    private void SwitchToProduse() throws IOException {
        App.setRoot("VizProduse.fxml");

    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
        App.setUser(null);
        App.getCos().clear();
    }

    public void adaugaInCos(ActionEvent actionEvent) {
        int c;
        Double x;
        if(p!=null) {
            c = Integer.parseInt(cantitate.getText());
            if (c <= p.getCantitate()) {
                if (c > 0) {
                    x=c*p.getPret();
                    Produs q = new Produs(p.getPret(), c, p.getDescriere(), p.getDenumire());
                    q.setId(p.getId());
                    App.getCos().add(q);
                    mesaj.setText("Produs adaugat cu succes!");
                    total.setText(x+"");
                }
                else mesaj.setText("Adaugati o cantitate corecta!");
            }
            else mesaj.setText("Cantitate indisponibila!");
        }
        else{
            mesajCauta.setText("Introduceti id-ul unui produs!");
        }

    }

    public void cautaProdus(ActionEvent actionEvent) {
        p=null;
        for(User user:vanzatori){
            for(Produs prod:user.getProduse())
                if(Objects.equals(idProdus.getText(),prod.getId())) {
                    p = prod;
                    mesajCauta.setText("Produs gasit!");
                    break;
                }
        }
        if(p==null){
            mesajCauta.setText("Produs inexistent!");
        }
    }
}
