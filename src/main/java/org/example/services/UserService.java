package org.example.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.models.Produs;
import org.example.models.User;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.services.FileSystemService.getPathToFile;


public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
         database = Nitrite.builder()
                .filePath(getPathToFile("User.db").toFile())
                .openOrCreate("test", "test");
        userRepository = database.getRepository(User.class);
    }

    public static void updateDatabase() {
        database.close();
        initDatabase();
    }

    public static ArrayList<User> getVanzatori(){
        ArrayList<User> vanzatori=new ArrayList<User>();
        for(User user:userRepository.find()){
            if(Objects.equals(user.getRole(),"Vanzator"))
                if(user.getProduse()!=null)
                    vanzatori.add(user);
        }
        return vanzatori;
    }

    public static int getLastIdOfProduct(User u){
        String s;
        String[] p;
        Produs prod;
        int x=-1;
        for(User user:userRepository.find()){
            if(Objects.equals(user.getUsername(),u.getUsername())){
                if(user.getProduse().isEmpty()==false) {
                    prod = user.getProduse().get(user.getProduse().size() - 1);
                    s = prod.getId();
                    p = s.split("@");
                    //System.out.println("UITE BA AICEA " + p[0] + p[1]);
                    x = Integer.parseInt(p[1]);
                }

            }
        }
        return x;
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }
    public static User getUser(String username){
        User x=null;
        for(User u:userRepository.find()){
            if(Objects.equals(u.getUsername(),username))
                x=u;
        }
        return x;
    }
    public static void updateUser(User v){
        userRepository.update(v);
    }

    public static User verifyCredentials(String username, String password) throws InvalidCredentialsException {
        password=encodePassword(username,password);
        try{
            checkUserDoesNotAlreadyExist(username);
            throw new InvalidCredentialsException();
            // return "Eroare";
        }
        catch (UsernameAlreadyExistsException e) {
            for (User user : userRepository.find()) {
                if (Objects.equals(username, user.getUsername())) {
                    if (Objects.equals(password, user.getPassword()) == false)
                        throw new InvalidCredentialsException();
                    else return user;
                }
            }
        }
        return new User();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
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