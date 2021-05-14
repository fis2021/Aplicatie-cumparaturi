package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import org.example.App;
import org.example.services.UserService;
import org.example.models.Comanda;
import org.example.services.ComandaService;


import java.io.IOException;
import java.util.ArrayList;

public class ComenziController {

    @FXML
    private TextField idComanda;
    @FXML
    private TextArea mesaj;
    @FXML
    private TextArea ListaComenzi;
    @FXML
    private Text mesaj1;
    @FXML
    private Text mesaj2;
    @FXML
    private RadioButton accept;
    @FXML
    private RadioButton decline;
    @FXML
    private ToggleGroup Raspuns;
    private static Comanda c;

    @FXML
    public void initialize(){
        ArrayList<Comanda> comenzi= ComandaService.getComenziVanzator(App.getUser());
        String s="Id comanda    Nume client    Nr.telefon    Adresa   Mod plata     Data    Acceptata/Respinsa  Sumar Produse \n ";
        for(Comanda c:comenzi){
            s=s+" "+c.getId()+"   "+c.getClient().getUsername()+"   "+c.getNrTelefon()+"   "+c.getAdresaLivrare()+"   "+c.getModPlata();
            s=s+"   "+c.getDataInregistrare()+"   "+c.getProduse()+"\n";
        }
        ListaComenzi.setText(s);




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
    private void SwitchToStergereProduse() throws IOException {
        App.setRoot("StergereProduse.fxml");
    }

    @FXML
    private void SwitchToAdaugareProduse() throws IOException {
        App.setRoot("AdaugareProduse.fxml");
    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
        UserService.updateUser(App.getUser());
        App.setUser(null);
    }

    @FXML
    public void cautaComanda() {
        c= ComandaService.getComanda(idComanda.getText());
        if(c==null)
            mesaj2.setText("Comanda inexistenta!");
        else mesaj2.setText("Comanda gasita!");
    }

    @FXML
    public void salveazaComanda() {
        String  a;
        if(c!= null){
            c.setMesaj(mesaj.getText());
            if(accept.isSelected())
                a="Acceptata";
            else a="Respinsa";
            c.setAcceptare(a);
            System.out.println(c.getId()+" "+c.getAdresaLivrare()+""+c.getAcceptare());
            ComandaService.updateComanda(c);
            mesaj1.setText("Comanda salvata cu succes!");
        }
    }


}