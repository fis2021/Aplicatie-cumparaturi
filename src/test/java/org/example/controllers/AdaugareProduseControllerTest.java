package org.example.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.App;
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

import static org.example.App.loadFXML;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class AdaugareProduseControllerTest {

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
    public void start(Stage stage) throws IOException {
        //FileSystemService.initDirectory();
        UserService.initDatabase();
        ComandaService.initDatabase();
        scene = new Scene(loadFXML("AdaugareProduse.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify AdaugaProduse tab is displayed")
    void testDysplayAdaugareProduse(FxRobot robot){
        robot.clickOn("#denumire").write("Proba");
    }

    @Test
    @DisplayName("Successfully adding a product")
    void testAddProduct1(FxRobot robot) throws Exception{
        UserService.addUser("Vanzator1","v1","Vanzator");
        App.setUser(UserService.getUser("Vanzator1"));
        robot.clickOn("#denumire").write("Mere Ionatan");
        robot.clickOn("#pret").write("1.5");
        robot.clickOn("#cantitate").write("200.0");
        robot.clickOn("#descriere").write("Mere ionatan, toamna 2020, origine: Romania");
        robot.clickOn("#adaugaButton");
        assertThat(robot.lookup("#message").queryText()).hasText("Produsul a fost adaugat cu succes!");
        assertThat(App.getUser().getProduse().get(0).getDenumire().equals("Mere Ionatan"));
        UserService.removeUsers();
    }

    @Test
    @DisplayName("Unsuccessfully adding a product")
    void testAddProduct2(FxRobot robot) throws Exception{
        UserService.addUser("Vanzator1","v1","Vanzator");
        App.setUser(UserService.getUser("Vanzator1"));
        robot.clickOn("#denumire").write("Mere Ionatan");
        robot.clickOn("#pret").write("1.5");
        robot.clickOn("#cantitate").write("200.0");
        //robot.clickOn("#descriere").write("Mere ionatan, toamna 2020, origine: Romania");
        robot.clickOn("#adaugaButton");
        assertThat(robot.lookup("#message").queryText()).hasText("Completati toate campurile!");
       // assertThat(App.getUser().getProduse().get(0).getDenumire().equals("Mere Ionatan"));
        UserService.removeUsers();
    }

}