package org.example.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.exceptions.OutOfStockException;
import org.example.models.Produs;
import org.example.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class CosCumparaturiController {

    @FXML
    private TextField idProdus;
    @FXML
    private TextField cantitateNoua;
    @FXML
    private TextArea ListaProduse;
    @FXML
    private Text mesajCautare;
    @FXML
    private Text mesajEliminare;
    @FXML
    private Text total;
    private static ArrayList<Produs> cos;
    private static Produs produs=null;
    @FXML
    public void initialize(){
        String s="Id   Denumire    Pret    Cantitate    Vanzator    Descriere\n";
        String[] s1;
        cos=App.getCos();
        double x=0;
        for(Produs p:cos){
            s1=p.getId().split("@");
            x=x+p.getPret()*p.getCantitate();
            s=s+p.getId()+"   "+p.getDenumire()+"   "+p.getPret()+"   "+p.getCantitate()+"  "+s1[0]+"   "+p.getDescriere()+"\n";
        }
        ListaProduse.setText(s);
        total.setText(x+"");
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
    public void SwitchTOLogin() throws IOException {
        App.setRoot("primary.fxml");
        App.setUser(null);
        App.golireCos();
    }

    public  void CautaProdus() {
        int i=0;
       // cos=App.getCos();
        if(idProdus.getText().isEmpty()==false) {
            for (Produs prod : App.getCos()) {
                if (prod.getId().equals(idProdus.getText()))
                {
                    produs = prod;
                    //System.out.println(idProdus.getText()+"\n"+prod.getId()+"\n");
                    i = 1;
                    mesajCautare.setText("Produs gasit!");
                    break;
                }
            }
            if (i == 0) mesajCautare.setText("Produs inexistent!");
        }
        else mesajCautare.setText("Introduceti id-ul produsului de modificat!");

    }

        /*int i=0;
       // cos=App.getCos();
        if(idProdus.getText().isEmpty()==false) {
            for (Produs prod : App.getCos()) {
                if (prod.getId().toString().equals(idProdus.getText())) ;
                {
                    produs = prod;
                    System.out.println(idProdus.getText()+"\n"+prod.getId()+"\n");
                    i = 1;
                    mesajCautare.setText("Produs gasit!");
                    break;
                }
            }
            if (i == 0) mesajCautare.setText("Produs inexistent!");
        }
        else mesajCautare.setText("Introduceti id-ul produsului de modificat!");
    }*/

    public void modificaCantitate() {
        CautaProdus();
        String []t;
        double cant=0;
        if(produs==null)
            mesajEliminare.setText("Introduceti id-ul produsului de modificat!");
        else {
            t=produs.getId().split("@");
            for(Produs x:UserService.getUser(t[0]).getProduse()){
                if(x.getId().equals(produs.getId())){
                    cant=x.getCantitate();
                    break;
                }
            }
            try {
                if(cantitateNoua.getText()!="" && cantitateNoua.getText() != null && cantitateNoua.getText().trim().isEmpty()==false){
                    if(Double.parseDouble(cantitateNoua.getText()) <= 0){
                        mesajEliminare.setText("Introduceti o cantitate corecta!");
                    }
                    else if(Double.parseDouble(cantitateNoua.getText())<=cant){
                        produs.setCantitate(Double.parseDouble(cantitateNoua.getText()));
                        initialize();
                    }
                    else mesajEliminare.setText("Cantitate indisponibila!");
                } else mesajEliminare.setText("Introduceti o cantitate corecta!");
            }catch (OutOfStockException e){
                mesajEliminare.setText("Nu puteti da numere negative!");
            }
        }

    }


    public void eliminareProdus() {
        CautaProdus();
        int i=cos.indexOf(produs);
        if(produs!=null) {
            cos.remove(produs);
            produs = null;
            initialize();
        }
        else mesajEliminare.setText("Selectati un produs!");
    }

    public void switchToPlasare() throws IOException {
        if(App.getCos().isEmpty()==true) mesajEliminare.setText("Cosul este gol!");
        else
            App.setRoot("PlasareComanda.fxml");
    }

}