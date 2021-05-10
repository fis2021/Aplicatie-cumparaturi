package org.example;

public class Produs {
    private static int cod=0;
    private double pret;
    private int cantitate;
    private String denumire;
    private String descriere;
    private String id;

    public Produs(){

    }

    public Produs(double pret,int cantitate, String descriere,String denumire){
        this.pret=pret;
        this.cantitate=cantitate;
        this.descriere= descriere;
        this.id="$"+cod;
        this.denumire=denumire;
        //this.vanzator=vanzator;
        cod++;
    }

    public double getPret(){ return pret; }
    public int getCantitate(){ return cantitate; }
    public String getDescriere(){ return descriere; }
    public String getId(){ return this.id;}
    public String getDenumire(){ return this.denumire; }

    public void setPret(int pret){ this.pret=pret; }
    public void setCantitate(int cantitate) throws OutOfStockException{
        if(this.cantitate + cantitate <0) throw new OutOfStockException();
    }
    public void setDescriere(String descriere) {
        this.descriere=descriere;
    }


    public void reducerePret(double procent){
        this.pret=(1+(procent/100))*this.pret;
    }

}
