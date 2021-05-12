package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.IOException;

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
        UserService.updateUser(App.user);
        App.setUser(null);
    }

    @FXML
    public void cautaComanda() {
        c=ComandaService.getComanda(idComanda.getText());
        if(c==null)
            mesaj2.setText("Comanda inexistenta!");
        else mesaj2.setText("Comanda gasita!");
    }

    @FXML
    public void salveazaComanda() {
        boolean a;
        if(c!= null){
            c.setMesaj(mesaj.getText());
            if(accept.isSelected())
                a=true;
            else a=false;
            c.setAcceptare(a);
            ComandaService.updateComanda(c);
            mesaj1.setText("Comanda slavata cu succes!");
        }
    }


}