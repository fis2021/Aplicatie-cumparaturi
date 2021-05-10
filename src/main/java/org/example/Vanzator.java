package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Vanzator extends User {

    public ArrayList<Produs> getProduse(){ return this.produse; }

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
