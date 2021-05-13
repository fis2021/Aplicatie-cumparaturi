package org.example;
import org.example.App;
import org.example.OutOfStockException;
public class Produs {
    private static int cod=0;
    private double pret;
    private double cantitate;
    private String denumire;
    private String descriere;
    private String id;
    public Produs(){

    }

    public Produs(double pret,double cantitate, String descriere,String denumire){
        this.pret=pret;
        this.cantitate=cantitate;
        this.descriere= descriere;
        cod=cod+1;
        this.id= App.getUser().getUsername()+"@"+cod;
        this.denumire=denumire;
        //this.vanzator=vanzator;
        //cod=cod+1;
    }
    public String toString(){
        String s;
        s=this.id+"   "+this.denumire+"  "+this.pret+"  "+this.cantitate+" "+this.descriere;
        s=s+"\n";
        return s;
    }

    public double getPret(){ return this.pret; }
    public double getCantitate(){ return this.cantitate; }
    public String getDescriere(){ return this.descriere; }
    public String getId(){ return this.id;}
    public String getDenumire(){ return this.denumire; }

    public void setPret(double pret){ this.pret=pret; }

    public void setCantitate(double cantitate) throws OutOfStockException {

        if(this.cantitate + cantitate <0) throw new OutOfStockException();
        else this.cantitate=cantitate;
    }
    public void addCantitate(double d){ this.cantitate=this.cantitate+d;}
    public void setDescriere(String descriere) {
        this.descriere=descriere;
    }
    public void setDenumire(String denumire){
        this.denumire=denumire;
    }
    public static void setCod(int cod){
        Produs.cod=cod;
    }
    public void setId(String id){ this.id=id; }


    public void reducerePret(double procent){
        this.pret=(1+(procent/100))*this.pret;
    }

}