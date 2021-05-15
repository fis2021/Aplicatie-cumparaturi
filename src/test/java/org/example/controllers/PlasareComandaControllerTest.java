package org.example.controllers;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.example.App;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.models.Produs;
import org.example.models.User;
import org.example.services.ComandaService;
import org.example.services.FileSystemService;
import org.example.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import static org.example.App.getCos;
import static org.example.App.loadFXML;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class PlasareComandaControllerTest {

    private static Scene scene;


    @BeforeAll
    static void beforeAll() throws IOException {
        FileSystemService.setApplicationFolder("AplicatieCumparaturi_test");
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
        FileSystemService.initDirectory();
    }

    @AfterAll
    static void afterAll()
            throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
    }

    @BeforeEach
    void setUp() {
        //App.golireCos();
        UserService.removeUsers();
    }


    @AfterEach
    void tearDown() {
        App.golireCos();
        UserService.removeUsers();
        UserService.closeDatabase();
        ComandaService.closeDatabase();
    }

    @Start
    public void start(Stage stage) throws Exception {
        //FileSystemService.initDirectory();

        UserService.initDatabase();
        ComandaService.initDatabase();
        // System.out.println(UserService.getUser("c1").getUsername());
        UserService.addUser("V1", "v1", "Vanzator");
        App.setUser(UserService.getUser("V1"));
        App.getUser().setProduseNull();
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));
        UserService.updateUser(App.getUser());
        UserService.addUser("c1", "c1", "Client");
        App.setUser(UserService.getUser("c1"));
        if(App.getCos().isEmpty()) {
            App.addCos(UserService.getUser("V1").getProduse().get(0));
            App.addCos(UserService.getUser("V1").getProduse().get(1));
        }

        //  System.out.println(App.getCos());
        scene = new Scene(loadFXML("PlasareComanda.fxml"), 950, 700);
        stage.setTitle("Aplicatie Cumparaturi -test");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify PlasareComanda tab is displayed")
    void testPlasareComandaDisplay(FxRobot robot){
      //  assertThat(robot.lookup("#total").queryText()).hasText("600.0 lei");
        assertThat(App.getCos().size()).isEqualTo(2);
    }

    @Test
    @DisplayName(" Verify place of an order - succesful scenrio")
    void testPlaceOrder(FxRobot robot){
        robot.clickOn("#nrTel").write("0727217187");
        robot.clickOn("#adresa").write("Timisoara, Cantacuzino nr 2");
        robot.clickOn("#plaseazaButton");

        assertThat(robot.lookup("#mesaj").queryText()).hasText("Comanda trimisa!");
        assertThat(ComandaService.getAllComanda().size()).isEqualTo(1);
    }
    @Test
    @DisplayName(" Verify place of an order - fail scenrio")
    void testPlaceOrder1(FxRobot robot){
        //by uncompleted adress
        robot.clickOn("#nrTel").write("0727217187");
        //robot.clickOn("#adresa").write("Timisoara, Cantacuzino nr 2");
        robot.clickOn("#plaseazaButton");

        assertThat(robot.lookup("#mesaj").queryText()).hasText("Completati datele!");

        App.golireCos();

    }

    @Test
    @DisplayName(" Verify place of an order - fail scenrio 2")
    void testPlaceOrder2(FxRobot robot){
        //by uncompleted phone number
        //robot.clickOn("#nrTel").write("0727217187");
        robot.clickOn("#adresa").write("Timisoara, Cantacuzino nr 2");
        robot.clickOn("#plaseazaButton");

        assertThat(robot.lookup("#mesaj").queryText()).hasText("Completati datele!");



    }
}