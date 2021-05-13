package org.example;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;


import javax.validation.constraints.Null;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.FileSystemService.getPathToFile;

public class ComandaService {

    private static ObjectRepository<Comanda> comandaRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Order.db").toFile())
                .openOrCreate("test2", "test2");
        comandaRepository = database.getRepository(Comanda.class);
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


}


