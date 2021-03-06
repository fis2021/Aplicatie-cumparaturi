package org.example.models;

import org.dizitart.no2.objects.Id;
import org.example.App;

import java.util.ArrayList;

public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private ArrayList<Produs> produse;
    private ArrayList<Comanda> comenzi;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        produse= new ArrayList<Produs>();
        comenzi= new ArrayList<Comanda>();
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public void addProduse(Produs p){
            produse.add(p);
    }

    public void setProduseNull(){
        this.produse.clear();
    }

    public ArrayList<Produs>getProduse(){ return this.produse; }

    public ArrayList<Comanda> getComenzi(){
        return this.comenzi;
    }

    public void PlasareComanda(ArrayList<Produs> produse,User vanzator,String NrTelefon,String Adresa,String modPlata,String dataInregistare,double total){
        Comanda c= new Comanda(produse,Adresa,modPlata," ",total, App.getUser(),vanzator,NrTelefon,dataInregistare);
        comenzi.add(c);
    }
}
