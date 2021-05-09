package org.example;

import java.util.ArrayList;

public class Comanda {
    private static int contor=0;
    private ArrayList<Produs> produse;
    private String adresaLivrare;
    private String modPlata;
    private String mesaj;
    private double totalPret;
    private String dataInregistrare;
    private Client client;
    private String NrTelefon;
    private Vanzator vanzator;
    private String Id;

    public Comanda(ArrayList<Produs> produse, String adresaLivrare, String modPlata,String mesaj,double total,Client client,Vanzator vanzator,String nrTelefon,String dataInregistrare){
        this.produse=produse;
        this.adresaLivrare=adresaLivrare;
        this.modPlata=modPlata;
        this.mesaj=mesaj;
        this.totalPret=total;
        this.client=client;
        this.vanzator=vanzator;
        this.NrTelefon=nrTelefon;
        this.dataInregistrare=dataInregistrare;

        this.Id="#"+contor;
        contor=contor+1;
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
    public Client getClient(){
        return this.client;
    }
    public Vanzator getVanzator(){ return this.vanzator; }
    public String getNrTelefon(){
        return this.NrTelefon;
    }
    public String getId(){
        return this.Id;
    }

    public void setMesaj(String mesaj){
        this.mesaj=mesaj;
    }

}
