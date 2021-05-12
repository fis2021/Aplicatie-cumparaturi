package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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
    private ArrayList<Produs> cos;
    private double x;

    public void initialize(){
        String s="Id   Denumire    Pret    Cantitate    Vanzator    Descriere\n";
        String[] s1;
        cos=App.getCos();

        for(Produs p:cos){
            s1=p.getId().split("@");
            x=x+p.getPret();
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
        String[]s;
        String s1;
        ArrayList<String> vizitat= new ArrayList<String>();
        double x;
        int aE=0,bE=0,cE=0;

        for(Produs prod:App.getCos()){
            x=0.0;
            aE=1;
            bE=1;
            cE=0;
            s1=prod.getId();
            s=s1.split("@");

            if(vizitat.isEmpty() || vizitat.indexOf(s[0])==-1){
                vizitat.add(s[0]);
                for(Produs p:App.getCos()){
                    if(p.getId().equals(s1)){
                        produs.add(p);
                        x=x+p.getPret();
                    }
                }

                if (nrTel.getText().equals("") == false) {
                    if (adresa.getText().equals("") == false) {
                        Comanda c = new Comanda(App.getCos(),adresa.getText(),"ramburs","",x,App.getUser(), UserService.getUser(s[0]) ,nrTel.getText(),d.toString());
                        c.setAcceptare("-");
                        ComandaService.addComanda(c);
                        cE=1;
                    } else bE=0;
                } else aE=0;

                if(aE==0){ mesaj.setText("Introduceti un numar de telefon!"); break;}
                if(bE==0) {mesaj.setText("Introduceti o adresa!"); break;}


            }
            mesaj.setText("Comanda trimisa!");

        }
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