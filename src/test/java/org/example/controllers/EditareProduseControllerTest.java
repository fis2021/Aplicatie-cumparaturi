package org.example.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
//import kotlin.internal.DynamicExtension;
import org.apache.commons.io.FileUtils;
import org.example.App;
import org.example.models.Produs;
import org.example.services.ComandaService;
import org.example.services.FileSystemService;
import org.example.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.example.App.loadFXML;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class EditareProduseControllerTest {

    private static Scene scene;

    @AfterAll
    static void afterAll()
            throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
    }

    @BeforeEach
    void setUp()
    {
        // UserService.removeUsers();
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
        UserService.addUser("V1","v1","Vanzator");
        App.setUser(UserService.getUser("V1"));
        App.getUser().addProduse(new Produs(1.0,200,"Mere romanesti ","Mere Golden"));
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(2.0,200,"Mere romanesti ","Mere Ionatan"));
        scene = new Scene(loadFXML("EditareProduse.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi -test");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify EditareProduse tab is displayed")
    void testDysplayAdaugareProduse(FxRobot robot){

    }

    @Test
    @DisplayName("Verify cautaProdus method and initialize succes scenario")
    void testCauta(FxRobot robot){

        robot.clickOn("#salveazaButton");
        assertThat(robot.lookup("#message").queryText()).hasText("Introduceti id-ul unui produs!");

        robot.clickOn("#idProdus").write("0");
        robot.clickOn("#cautaButton");
        robot.clickOn("#descriere").write("golden");
        robot.clickOn("#cantitate").write("1");
        robot.clickOn("#salveazaButton");
        assertThat(robot.lookup("#message").queryText()).hasText("Editare cu succes!");
        assertThat(App.getUser().getProduse().get(0).getDescriere().equals("Mere romaesti golden"));

    }
    @Test
    @DisplayName("Verify cautaProdus method and initialize fail scenario")
    void testCauta1(FxRobot robot) {

        robot.clickOn("#salveazaButton");
        assertThat(robot.lookup("#message").queryText()).hasText("Introduceti id-ul unui produs!");
    }



    }