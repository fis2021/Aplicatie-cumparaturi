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
class VizProduseControllerTest {

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

        UserService.removeUsers();
    }


    @AfterEach
    void tearDown() {
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
        UserService.addUser("c1","c1","Client");
        App.setUser(UserService.getUser("c1"));

      //  System.out.println(App.getCos());
        scene = new Scene(loadFXML("VizProduse.fxml"), 950, 700);
        stage.setTitle("Aplicatie Cumparaturi -test");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify VizProduse tab is displayed and initialize method")
    void testDisplayVizProduse(FxRobot robot){
        assertThat(robot.lookup("#mesaj").queryText()).hasText("");
        assertThat(robot.lookup("#mesajCauta").queryText()).hasText("");
    }

    @Test
    @DisplayName("Verify cautaProdus method")
    void testCautaProdus(FxRobot robot) throws UsernameAlreadyExistsException {
        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cautaButton");
        assertThat(robot.lookup("#mesajCauta").queryText()).hasText("Produs gasit!");
        robot.clickOn("#idProdus");
        robot.eraseText(4);
        robot.clickOn("#idProdus").write("V1@2");
        robot.clickOn("#cautaButton");
        assertThat(robot.lookup("#mesajCauta").queryText()).hasText("Produs inexistent!");

    }
    @Test
    @DisplayName("Verify adaugaInCos method successfully scenario")
    void testAdaugaInCos(FxRobot robot){
        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cantitate").write("20");
        robot.clickOn("#cautaButton");
        robot.clickOn("#adaugaCosButton");
        assertThat(robot.lookup("#total").queryText()).hasText("20.0");
        robot.clickOn("#idProdus");
        robot.eraseText(1);
        robot.write("1");
        robot.clickOn("#cautaButton");
        robot.clickOn("#cantitate").eraseText(2);
        robot.clickOn("#cantitate").write("20");
        robot.clickOn("#adaugaCosButton");
        assertThat(robot.lookup("#total").queryText()).hasText("40.0");
        robot.clickOn("#idProdus");
        robot.eraseText(1);
        robot.write("0");
        robot.clickOn("#cautaButton");
        robot.clickOn("#cantitate").eraseText(2);
        robot.clickOn("#cantitate").write("10");
        robot.clickOn("#adaugaCosButton");

        assertThat(robot.lookup("#mesaj").queryText()).hasText("Produs adaugat cu succes!");
        assertThat(App.getCos().size()).isEqualTo(2);
        assertThat(App.getCos().get(0).getCantitate()).isEqualTo(30);
        assertThat(App.getCos().get(1).getCantitate()).isEqualTo(20);
        assertThat(robot.lookup("#total").queryText()).hasText("10.0");
    }

    @Test
    @DisplayName("Verify adaugaInCos method fail scenario")
    void testAdaugaInCos1(FxRobot robot){
        robot.clickOn("#idProdus").write("V1@0");
        robot.clickOn("#cantitate").write("201");
        robot.clickOn("#cautaButton");
        robot.clickOn("#adaugaCosButton");

        assertThat(robot.lookup("#mesaj").queryText()).hasText("Cantitate indisponibila!");

        robot.clickOn("#cantitate").eraseText(3);
        robot.clickOn("#cantitate").write("-1");
        robot.clickOn("#adaugaCosButton");
        assertThat(robot.lookup("#mesaj").queryText()).hasText("Adaugati o cantitate corecta!");

        robot.clickOn("#idProdus").eraseText(3);
        robot.clickOn("#cautaButton");
        robot.clickOn("#cantitate").eraseText(2);
        robot.write("1");
        robot.clickOn("#adaugaCosButton");
        assertThat(robot.lookup("#mesajCauta").queryText()).hasText("Introduceti id-ul unui produs!");


    }

}
