package org.example.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.example.App.loadFXML;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class CosCumparaturiControllerTest {

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
    void setUp() throws Exception
    {


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
        UserService.addUser("V1", "v1", "Vanzator");
        App.setUser(UserService.getUser("V1"));
        App.getUser().setProduseNull();
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));
        UserService.updateUser(App.getUser());
        UserService.addUser("c1", "c1", "Client");
        // System.out.println(UserService.getUser("c1").getUsername());
        App.setUser(UserService.getUser("c1"));
        App.addCos(UserService.getUser("V1").getProduse().get(0));
        App.addCos(UserService.getUser("V1").getProduse().get(1));

        //  System.out.println(App.getCos());
        scene = new Scene(loadFXML("CosCumparaturi.fxml"), 950, 700);
        stage.setTitle("Aplicatie Cumparaturi -test");
        stage.setScene(scene);
        stage.show();
    }


    @Test
    @DisplayName("Verify CosCumparaturiController tab is displayed and initialize method")
    void testDisplayCosCumparaturi(FxRobot robot) {
        assertThat(robot.lookup("#mesajEliminare").queryText()).hasText("");
        assertThat(robot.lookup("#total").queryText()).hasText("600.0");
    }

    @Test
    @DisplayName("Verify cautaProdus method")
    void testCautaProdus(FxRobot robot) throws UsernameAlreadyExistsException {
        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cautaButton");

        assertThat(robot.lookup("#mesajCautare").queryText()).hasText("Produs gasit!");

        robot.clickOn("#idProdus");
        robot.eraseText(4);
        robot.clickOn("#idProdus").write("V1@3");
        robot.clickOn("#cautaButton");

        assertThat(robot.lookup("#mesajCautare").queryText()).hasText("Produs inexistent!");

        robot.clickOn("#idProdus");
        robot.eraseText(4);
        robot.clickOn("#idProdus").write("V1@1");
        robot.clickOn("#cautaButton");
        assertThat(robot.lookup("#mesajCautare").queryText()).hasText("Produs gasit!");

    }

    @Test
    @DisplayName("Verify modificaCantitae - successful scenario")
    void testModificaCantitate(FxRobot robot) throws Exception{
     //   System.out.println(UserService.getUser("V1").getProduse());
         UserService.addUser("V1","V1","Vanzator");
        User v=UserService.getUser("V1");
        App.setUser(v);
        v.addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        v.addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));

        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cautaButton");
        robot.clickOn("#cantitateNoua").write("10");
        robot.clickOn("#modificaButton");

        assertThat(App.getCos().get(0).getCantitate()).isEqualTo(200.0);
       // assertThat(robot.lookup("#total").queryText()).hasText("410.0");

    }

    @Test
    @DisplayName("Verify modificaCantitatea- fail scenario")
    void testModificaCantitatea1(FxRobot robot) throws Exception{
        //fail with wrong quantity
        UserService.addUser("V1","V1","Vanzator");
        User v=UserService.getUser("V1");
        App.setUser(v);
        v.addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        v.addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));

        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cautaButton");
        robot.clickOn("#modificaButton");

        assertThat(robot.lookup("#mesajEliminare").queryText()).hasText("Introduceti o cantitate corecta!");

        //fail with to large quantity
        robot.clickOn("#cantitateNoua").write("1000");
        //robot.clickOn("#cautaButton");
        robot.clickOn("#modificaButton");

       assertThat(robot.lookup("#mesajEliminare").queryText()).hasText("Cantitate indisponibila!");

    }

    @Test
    @DisplayName("Verify eliminareProdus method- successful scenario")
    void testEliminareProdus(FxRobot robot) throws Exception{
        UserService.addUser("V1","V1","Vanzator");
        User v=UserService.getUser("V1");
        App.setUser(v);
        v.addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        v.addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));
// first item on cart
        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cautaButton");
        robot.clickOn("#eliminaButton");

        assertThat(App.getCos().size()).isEqualTo(1);
//second item on cart
        robot.clickOn("#cautaButton");
        robot.clickOn("#idProdus").eraseText(4);
        robot.clickOn("#idProdus").write("V1@1");
        robot.clickOn("#cautaButton");
        robot.clickOn("#eliminaButton");

        assertThat(App.getCos().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Verify eliminareProdus method- fail scenario")
    void testEliminareProdus1(FxRobot robot) throws Exception{
        UserService.addUser("V1","V1","Vanzator");
        User v=UserService.getUser("V1");
        App.setUser(v);
        v.addProduse(new Produs(1.0, 200, "Mere romanesti ", "Mere Golden"));
        v.addProduse(new Produs(2.0, 200, "Mere romanesti ", "Mere Ionatan"));

        robot.clickOn("#eliminaButton");

        assertThat(robot.lookup("#mesajCautare").queryText()).hasText("Introduceti id-ul produsului de modificat!");

    }



}