package org.example;

import java.util.ArrayList;

public class Client extends User {


        public void PlasareComanda(ArrayList<Produs> produse,Vanzator vanzator,String NrTelefon,String Adresa,String modPlata,String dataInregistare,double total){
            Comanda c= new Comanda(produse,Adresa,modPlata," ",total,this,vanzator,NrTelefon,dataInregistare);
            comenzi.add(c);
        }

        public ArrayList<Comanda> getComenzi(){
            return this.comenzi;
        }

        public void EditProdus(int idProdus){

        }
}

