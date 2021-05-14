package org.example.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.exceptions.OutOfStockException;
import org.example.models.Produs;
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
    private Label total;
    private ArrayList<Produs> cos;
    private Produs produs=null;
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

    public void CautaProdus(ActionEvent actionEvent) {

        int i=0;
        if(idProdus.getText().isEmpty()==false) {
            for (Produs p : cos) {
                if (Objects.equals(p.getId(), idProdus.getText())) ;
                {
                    produs = p;
                    i = 1;
                    mesajCautare.setText("Produsul gasit!");
                    break;
                }
            }
            if (i == 0) mesajCautare.setText("Produs inexistent!");
        }
        else mesajCautare.setText("Introduceti id-ul produsului de modificat!");
    }

    public void modificaCantitate(ActionEvent actionEvent) {
        if(produs==null)
            mesajEliminare.setText("Introduceti id-ul produsului de modificat!");
        else {
            try {
                if(Objects.equals(cantitateNoua.getText(),"0")){
                    mesajEliminare.setText("Puteti sterge produsul daca apasati butonul de mai sus!");
                }
                else {
                    produs.setCantitate(Double.parseDouble(cantitateNoua.getText()));
                    initialize();
                }
            }catch (OutOfStockException e){
                mesajEliminare.setText("Nu puteti da numere negative!");
            }
        }

    }

    public void eliminareProdus(ActionEvent actionEvent) {
        if(produs!=null) {
            cos.remove(produs);
            produs = null;
            initialize();
        }
        else mesajEliminare.setText("Selectati un produs!");
    }

    public void switchToPlasare(ActionEvent actionEvent) throws IOException {
        if(App.getCos().isEmpty()==true) mesajEliminare.setText("Cosul este gol!");
        else
            App.setRoot("PlasareComanda.fxml");
    }
}