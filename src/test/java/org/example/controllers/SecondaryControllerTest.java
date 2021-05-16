package org.example.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.App;
import org.example.models.User;
import org.example.services.ComandaService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.example.services.FileSystemService;
import org.example.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.example.App.loadFXML;
import static org.testfx.assertions.api.Assertions.assertThat;



import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SecondaryControllerTest {
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
        UserService.removeUsers();
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
        scene = new Scene(loadFXML("secondary.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("An succesfully register")
    void testDemo1(FxRobot robot){
        //UserService.addUser("Admin","Admin","ADMIN");
        robot.clickOn("#usernameR").write("v1");
        robot.clickOn("#passwordR").write("v1");
        robot.clickOn("#role").clickOn("Vanzator");
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#messageR").queryText()).hasText("Account created successfully!");
        /*robot.write("Admin");
        robot.clickOn("#password");
        robot.write("Admin10909090");
        robot.clickOn("#login");
        assertThat(robot.lookup("#mesaj").queryText()).hasText("Username or Password is incorrect!");*/
    }

    @Test
    @DisplayName("An unsuccessfully register")
    void testDemo2(FxRobot robot){
        robot.clickOn("#usernameR");
        //robot.clickOn("#passwordR");
        //robot.clickOn("#role");
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#messageR").queryText()).hasText("Completati toate campurile!");
    }
    @Test
    @DisplayName("An unsuccessfully register with already taken uername")
    void testDemo3(FxRobot robot) throws Exception{
        UserService.addUser("Proba","Proba","Vanzator");
        robot.clickOn("#usernameR").write("Proba");
        robot.clickOn("#passwordR").write("Proba");
        robot.clickOn("#role").clickOn("Client");
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#messageR").queryText()).hasText(String.format("An account with the username Proba already exists!"));
        UserService.removeUsers();
    }


}