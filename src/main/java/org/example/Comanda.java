package org.example;

import org.dizitart.no2.objects.Id;
import java.util.ArrayList;
public class Comanda {
    @Id
    private String codUnic;
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
    private String acceptata;
    public Comanda(){
    }
    public Comanda(ArrayList<Produs> produse, String adresaLivrare, String modPlata, String mesaj, double total, User client, User vanzator, String nrTelefon, String dataInregistrare){
        ArrayList<Comanda> com=ComandaService.getComenziClient(client);
        String t[];
        String s;
        this.produse=produse;
        this.adresaLivrare=adresaLivrare;
        this.modPlata=modPlata;
        this.mesaj=mesaj;
        this.totalPret=total;
        this.client=client;
        this.vanzator=vanzator;
        this.NrTelefon=nrTelefon;
        this.dataInregistrare=dataInregistrare;
        if(com.size()!=0){
            s= com.get(com.size()-1).getId();
            t=s.split("#");
            contor=Integer.parseInt(t[1])+1;}
        else{
            contor=0;
        }
        this.codUnic=client.getUsername()+"#"+contor;
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
        return this.codUnic;
    }
    public String getAcceptare(){ return this.acceptata;}
    public double getTotal(){ return this.totalPret;}
    public ArrayList<Produs> getProduse(){ return this.produse;}
    public void setMesaj(String mesaj){
        this.mesaj=mesaj;
    }
    public void setAcceptare(String  x){ this.acceptata=x; }

}