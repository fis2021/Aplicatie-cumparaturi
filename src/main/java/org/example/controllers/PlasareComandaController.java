package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.models.Produs;
import org.example.services.UserService;
import org.example.services.ComandaService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlasareComandaController {
    @FXML
    private TextField nrTel;
    @FXML
    private TextArea adresa;
    @FXML
    private TextArea ListaProd;
    @FXML
    private Text mesaj;
    @FXML
    private Label total;
    private static ArrayList<Produs> cos;
    private static double x;

    public void initialize(){
        String s="Id   Denumire    Pret    Cantitate    Vanzator    Descriere\n";
        String[] s1;
        cos= App.getCos();

        for(Produs p:cos){
            s1=p.getId().split("@");
            x=x+p.getPret()*p.getCantitate();
            s=s+p.getId()+"   "+p.getDenumire()+"   "+p.getPret()+"   "+p.getCantitate()+"  "+s1[0]+"   "+p.getDescriere()+"\n";
        }
        ListaProd.setText(s);
        total.setText(x+" lei");
    }

    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
    }
    @FXML
    private void SwitchToIstoric() throws IOException {
        App.setRoot("IstoricComenzi.fxml");
    }
    @FXML
    private void SwitchToProduse() throws IOException {
        App.setRoot("VizProduse.fxml");
    }

    @FXML
    private void SwitchToLogin() throws IOException{
        App.setRoot("primary.fxml");
        App.setUser(null);
    }

    public void placeOrder() {
        LocalDateTime d=LocalDateTime.now();
        ArrayList<Produs> produs= new ArrayList<Produs>();
        String[]s,s2;
        String s1;
        ArrayList<String> vizitat= new ArrayList<String>();
        double x;
        //int aE=0,bE=0,cE=0;

        for(Produs prod:App.getCos()){
            x=0.0;
            s1=prod.getId();
            s=s1.split("@");
            produs.clear();

            if(vizitat.isEmpty() || vizitat.indexOf(s[0])==-1){
                vizitat.add(s[0]);
                for(Produs p:App.getCos()){
                    s2=p.getId().split("@");
                    if(s2[0].equals(s[0])){
                        produs.add(p);
                        x=x+p.getPret()*p.getCantitate();
                    }
                }
               ComandaService.addComanda(produs,adresa.getText(),"Ramburs","-",x,App.getUser(), UserService.getUser(s[0]),nrTel.getText(),d.toString());

            }


        }mesaj.setText("Comanda trimisa!");
        /*
            if (nrTel.getText().equals("") == false) {
                if (adresa.getText().equals("") == false) {
                    Comanda c = new Comanda(App.getCos(),adresa.getText(),"ramburs","",x,App.getUser(),nrTel.getText(),d.toString());
                    ComandaService.addComanda(c);
                    mesaj.setText("Comanda trimisa !");
                } else mesaj.setText("Introduceti o adresa!");
            } else mesaj.setText("Introduceti un numar de telefon!");*/

    }

}