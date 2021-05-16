package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.example.App;
import org.example.models.Produs;
import org.example.models.Comanda;
import org.example.services.ComandaService;

import java.io.IOException;
import java.util.ArrayList;

public class IstoricComenziController {
    @FXML
        private TextArea ListaComenzi;
    @FXML
        private TextField idComanda;
    @FXML
        private TextField adresa;
    @FXML
        private TextField nrTel;
    @FXML
        private TextField modPlata;
    @FXML
        private TextField data;
    @FXML
        private TextField vanzator;
    @FXML
        private TextField status;
    @FXML
        private TextField mesaj;
    @FXML
        private Text mesajE;
    @FXML
        private TextField total;
    @FXML
    private TextArea listaProduse;
    private static ArrayList<Comanda> comenzi;
   // private ArrayList<String> P;

    @FXML
    public void initialize(){
        String s="Id comanda  Vanzator  Total  Data plasarii  Status  Sumar Produse\n";
        String a;
        comenzi= ComandaService.getComenziClient(App.getUser());
        for(Comanda c:comenzi)
        {
            s=s+" "+c.getId()+"   "+c.getVanzator().getUsername()+"   "+c.getTotal()+"   "+c.getDataInregistrare();
            if(c.getAcceptare()==null)
                a="Neprocesata";
            else a=c.getAcceptare();
            s=s+"   "+a+"  "+c.getProduse()+"\n";
          //  P.add(c.getProduse().toString());
        }
        ListaComenzi.setText(s);
    }

    @FXML
    public void SwitchToHome() throws IOException {
        App.setRoot("HomePageClient.fxml");
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
        App.golireCos();
    }

    public void cautaComanda() {
        String s1="Id produs   Denumire    Pret   Cantitate   Descriere\n";
        comenzi=ComandaService.getComenziClient(App.getUser());
        int x=0;
        for(Comanda c:comenzi){
            if(c.getId().equals(idComanda.getText()))
            {
                x=1;
                adresa.setText(c.getAdresaLivrare());
                nrTel.setText(c.getNrTelefon());
                modPlata.setText(c.getModPlata());
                data.setText(c.getDataInregistrare());
                vanzator.setText(c.getVanzator().getUsername());
                status.setText(c.getAcceptare());
                mesaj.setText(c.getMesaj());
                total.setText(c.getTotal()+"");
                for(Produs p:c.getProduse()){
                    s1=s1+p.getId()+"   "+p.getDenumire()+"   "+p.getPret()+"   "+p.getCantitate();
                    s1=s1+"  "+p.getDescriere()+"\n";
                }
                listaProduse.setText(s1);
                mesajE.setText("Mai jos aveti informatii legate de comanda!");


            }
        }
        if(x==0) mesajE.setText("Id comanda incorect!");
    }
}
