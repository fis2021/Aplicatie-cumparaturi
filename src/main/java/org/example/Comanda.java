package org.example;


import java.util.ArrayList;

public class Comanda {
    private static int contor;
    private ArrayList<Produs> produse;
    private String adresaLivrare;
    private String modPlata;
    private String mesaj;
    private double totalPret;
    private String dataInregistrare;
    private User client;
    private User vanzator;
    private String NrTelefon;
    private String Id;
    private String acceptata;

    public Comanda(){

    }

    public Comanda(ArrayList<Produs> produse, String adresaLivrare, String modPlata, String mesaj, double total, User client, User vanzator, String nrTelefon, String dataInregistrare){
        this.produse=produse;
        this.adresaLivrare=adresaLivrare;
        this.modPlata=modPlata;
        this.mesaj=mesaj;
        this.totalPret=total;
        this.client=client;
        this.vanzator=vanzator;
        this.NrTelefon=nrTelefon;
        this.dataInregistrare=dataInregistrare;
        contor= ComandaService.lastComanda(client)+1;

        this.Id="#"+contor;
        //contor=contor+1;
    }

    public String getAdresaLivrare(){
        return this.adresaLivrare;
    }
    public String getModPlata() {
        return this.modPlata;
    }
    public String getMesaj() {
        return this.mesaj;
    }
    public String getDataInregistrare() {
        return this.dataInregistrare;
    }
    public User getClient(){
        return this.client;
    }
    public User getVanzator(){
        return this.vanzator;
    }
    public String getNrTelefon(){
        return this.NrTelefon;
    }
    public String getId(){
        return this.Id;
    }
    public String getAcceptare(){ return this.acceptata;}

    public void setMesaj(String mesaj){
        this.mesaj=mesaj;
    }
    public void setAcceptare(String  x){ this.acceptata=x; }


}
