package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Vanzator extends User {

    public void addProduse(Produs p){
        produse.add(p);
    }
    public ArrayList<Comanda> getComenzi(){
        return this.comenzi;
    }

    public void editProdus(String idProdus, int pret,int cantitate,String descriere){
        try {
            for (Produs prod : produse) {
                if (prod.getId() == idProdus) {
                    prod.setDescriere(descriere);
                    prod.setPret(pret);
                    prod.setCantitate(cantitate);
                }
            }
        }catch(OutOfStockException e){
            System.out.println(e.getMessage());
        }
    }
}
