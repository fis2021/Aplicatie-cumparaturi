package org.example.services;

import kotlin.UseExperimental;
import org.apache.commons.io.FileUtils;
import org.dizitart.no2.UpdateOptions;
import org.example.App;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.models.Comanda;
import org.example.models.Produs;
import org.example.models.User;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComandaServiceTest {
    public static final String C1="c1";

    @BeforeAll
    static void beforeAll() {
        FileSystemService.setApplicationFolder(".AplicatieCumparaturi_test");
        FileSystemService.initDirectory();
    }

    @AfterAll
    static void afterAll()
            throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
    }

    @BeforeEach
    void setUp()
            throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
        UserService.initDatabase();
        ComandaService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.removeUsers();
        UserService.closeDatabase();
        ComandaService.removeAllComanda();
        ComandaService.closeDatabase();

    }
   /* @BeforeAll
    static void beforeAll() throws Exception{
        System.out.println("Before Class");
        FileSystemService.APPLICATION_FOLDER = ".test-Aplicatie-Cumparaturi";
        //UserService.initDatabase();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        // FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ComandaService.initDatabase();
    }*/
    @Test
    @DisplayName("Database is initialized and there are no orders.")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        //UserService.initDatabase();
        assertThat(ComandaService.getAllComanda()).isNotNull();
        assertThat(ComandaService.getAllComanda()).isEmpty();
    }

    @Test
    @DisplayName("Try: order is successfully persisted to Database, lastComanda and updateComanda")
    void testComandaIsAddedToDatabase() throws UsernameAlreadyExistsException {
       UserService.addUser("c1","c1","Client");
       UserService.addUser("v1","v1","Vanzator");
       App.setUser(UserService.getUser("v1"));
      // System.out.println(App.getUser().getUsername());
       Produs p=new Produs(1.0,10.0,"Descriere","Mere");
      // System.out.println()
       ArrayList<Produs> l=new ArrayList<>();
       App.setUser(UserService.getUser("c1"));
       Produs p1=new Produs(1.0,2.0,"Descriere","Mere");
       p1.setId(p.getId());
       l.add(p1);
       UserService.getUser("v1").addProduse(p);
       ComandaService.addComanda(l,"Timisoara, Nr 1 ","Ramburs","-",2.0,App.getUser(),UserService.getUser("v1"),"0727271143","12:30");

       assertThat(ComandaService.getAllComanda()).isNotNull();
       assertThat(ComandaService.getAllComanda().size()).isEqualTo(1);
       assertThat(ComandaService.getAllComanda().get(0).getAdresaLivrare()).isEqualTo("Timisoara, Nr 1 ");
       assertThat(ComandaService.getAllComanda().get(0).getModPlata()).isEqualTo("Ramburs");
       assertThat(ComandaService.getAllComanda().get(0).getMesaj()).isEqualTo("-");
       assertThat(ComandaService.getAllComanda().get(0).getTotal()).isEqualTo(2.0);
       assertThat(ComandaService.getAllComanda().get(0).getClient().getUsername()).isEqualTo(App.getUser().getUsername());
       assertThat(ComandaService.getAllComanda().get(0).getVanzator().getUsername()).isEqualTo("v1");
       assertThat(ComandaService.getAllComanda().get(0).getNrTelefon()).isEqualTo("0727271143");
       assertThat(ComandaService.getAllComanda().get(0).getDataInregistrare()).isEqualTo("12:30");
       assertThat(ComandaService.getAllComanda().get(0).getProduse().get(0).getId()).isEqualTo(p.getId());

       // lastComanda testing
        int i=ComandaService.lastComanda(App.getUser());
        Comanda x= ComandaService.getComenziClient(App.getUser()).get(0);
        String s=App.getUser().getUsername();
        assertThat(x.getId()).isEqualTo(s+"#"+i);

        // updateComanda
        x.setAcceptare("Preluata");
        ComandaService.updateComanda(x);
        Comanda y= ComandaService.getAllComanda().get(0);
        assertThat(y.getAcceptare()).isEqualTo("Preluata");


    }
    /*@Test
    @DisplayName("Order is succesfully persisted to database , last comanda from an client and update comanda")
    void testOrderIsAddedToDatabase() throws Exception{
       /* UserService.addUser(C1,C1,"Client");
        App.setUser(UserService.getUser("C1"));
        UserService.addUser("v1","v1","Vanzator");
        ArrayList<Produs> prod = new ArrayList<>();
        Produs p1,p2;
        p1=new Produs(1.2,20.0,"Descriere","Produs de proba");
        UserService.updateUser(App.getUser());
        p2=new Produs(1.5,20.0,"Descriere2","Produs de proba2");
        prod.add(p1);
        prod.add(p2);
       // Comanda c= new Comanda(prod,"AdresaProba","ramburs","",54.0,App.getUser(),v,"0727217165","azi");
        ComandaService.addComanda(prod,"AdresaProba","ramburs","",54.0,App.getUser(),UserService.getUser("v1"),"0727217165","azi");
        assertThat(ComandaService.getAllComanda()).isNotEmpty();
        assertThat(ComandaService.getAllComanda()).size().isEqualTo(1);
        Comanda x  = ComandaService.getAllComanda().get(0);
        assertThat(x).isNotNull();
        assertThat(x.getAdresaLivrare()).isEqualTo("AdresaProba");
        assertThat(x.getModPlata()).isEqualTo("ramburs");
        assertThat(x.getNrTelefon()).isEqualTo("0727217165");
        assertThat(x.getDataInregistrare()).isEqualTo("azi");
        assertThat(x.getProduse().get(0).getId()).isEqualTo(p1.getId());
        assertThat(x.getProduse().get(1).getId()).isEqualTo(p2.getId());
        assertThat(x.getClient()).isEqualTo(App.getUser());
        assertThat(x.getVanzator()).isEqualTo(UserService.getUser("v1"));
        assertThat(x.getTotal()).isEqualTo(54.0);
        // last comanda testing
        int i=ComandaService.lastComanda(App.getUser());
        String s=App.getUser().getUsername();
        assertThat(x.getId()).isEqualTo(s+"#"+i);
        // update comanda
        x.setAcceptare("Preluata");
        ComandaService.updateComanda(x);
        Comanda y= ComandaService.getAllComanda().get(0);
        assertThat(y.getAcceptare()).isEqualTo("Preluata");*/
    }
