package org.example.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.example.models.Comanda;
import org.example.models.Produs;
import org.example.models.User;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.services.FileSystemService.getPathToFile;

public class ComandaService {

    private static ObjectRepository<Comanda> comandaRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
         database = Nitrite.builder()
                .filePath(getPathToFile("Order.db").toFile())
                .openOrCreate("test2", "test2");
        comandaRepository = database.getRepository(Comanda.class);
    }

    public static void updateDatabase(){
        database.close();
        initDatabase();
    }

    public static List<Comanda> getAllComanda() {
        return comandaRepository.find().toList();
    }
    public static void removeAllComanda(){
         if(comandaRepository.find() != null)
            comandaRepository.remove(ObjectFilters.ALL);
    }

    public static ArrayList<Comanda> getComenziClient(User client) {
        ArrayList<Comanda> comenzi = new ArrayList<Comanda>();
        for (Comanda c : comandaRepository.find()) {
            if (Objects.equals(c.getClient().getUsername(), client.getUsername())) {
                comenzi.add(c);
            }
        }
        return comenzi;
    }

    public static void addComanda(ArrayList<Produs> produse, String adresaLivrare, String modPlata, String mesaj, double total, User client, User vanzator, String nrTelefon, String dataInregistrare){
        comandaRepository.insert(new Comanda(produse,adresaLivrare,modPlata,mesaj,total,client,vanzator,nrTelefon,dataInregistrare));
    }

    public static void updateComanda(Comanda c){
        comandaRepository.update(c);
    }

    public static int lastComanda(User p){
        String s="null";
        String[] parti;
        int x=0;
        for(Comanda c:comandaRepository.find()){
            if(Objects.equals(p.getUsername(),c.getClient())){
                s=c.getId();
            }
        }
        if(s!="null") {
            parti = s.split("#");
            x=Integer.parseInt(parti[1]);
        }
        return x;

    }

    public static ArrayList<Comanda> getComenziVanzator(User vanzator){
        ArrayList<Comanda> comenzi= new ArrayList<Comanda>();
        for(Comanda c:comandaRepository.find()){
            if(Objects.equals(c.getVanzator().getUsername(),vanzator.getUsername()))
            {
                comenzi.add(c);
            }
        }
        return comenzi;
    }


    public static Comanda getComanda(String id){
        Comanda x=null;
        for(Comanda c:comandaRepository.find()){
            if(Objects.equals(id,c.getId()))
                x=c;
        }
        return x;
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static void closeDatabase() {
        database.close();
    }
}


