package org.example.services;

import org.apache.commons.io.FileUtils;
import org.example.App;
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
        FileSystemService.setApplicationFolder("AplicatieCumparaturi_test");
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
        ComandaService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        ComandaService.closeDatabase();
        //SessionService.destroySession();
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
    @DisplayName("Order is succesfully persisted to database , last comanda from an client and update comanda")
    void testOrderIsAddedToDatabase(){
        App.setUser( new User(C1,C1,C1));
        User v= new User("v1","v1","v1");
        ArrayList<Produs> prod = new ArrayList<>();
        Produs p1,p2;
        p1=new Produs(1.2,20.0,"Descriere","Produs de proba");
        p2=new Produs(1.5,20.0,"Descriere2","Produs de proba2");
        prod.add(p1);
        prod.add(p2);
       // Comanda c= new Comanda(prod,"AdresaProba","ramburs","",54.0,App.getUser(),v,"0727217165","azi");
        ComandaService.addComanda(prod,"AdresaProba","ramburs","",54.0,App.getUser(),v,"0727217165","azi");
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
        assertThat(x.getVanzator()).isEqualTo(v);
        assertThat(x.getTotal()).isEqualTo(54.0);
        // last comanda testing
        int i=ComandaService.lastComanda(App.getUser());
        String s=App.getUser().getUsername();
        assertThat(x.getId()).isEqualTo(s+"#"+i);
        // update comanda
        x.setAcceptare("Preluata");
        ComandaService.updateComanda(x);
        Comanda y= ComandaService.getAllComanda().get(0);
        assertThat(y.getAcceptare()).isEqualTo("Preluata");
    }


}