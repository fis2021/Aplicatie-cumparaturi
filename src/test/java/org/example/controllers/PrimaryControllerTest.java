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
class PrimaryControllerTest {
   private static Scene scene;

    static void beforeAll()
            throws Exception {
        FileSystemService.setApplicationFolder("AplicatieCumparaturi_test");
        FileSystemService.initDirectory();
        UserService.initDatabase();
    }

    @AfterAll
    static void afterAll()
            throws IOException {
       /* FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());*/
    }

    @BeforeEach
    void setUp()
    {
        UserService.removeUsers();
    }
         /*   throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath()
                .toFile());
        UserService.initDatabase();*/
    //}

    @AfterEach
    void tearDown() {
        UserService.removeUsers();
        UserService.closeDatabase();
    }

   /* @BeforeEach
   void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-Aplicatie-cumparaturi";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }*/

    @Start
    public void start(Stage stage) throws IOException {
       //FileSystemService.initDirectory();
        UserService.initDatabase();
        ComandaService.initDatabase();
        scene = new Scene(loadFXML("primary.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testDemo(FxRobot robot) throws Exception{
        UserService.addUser("Admin","Admin","ADMIN");
       robot.clickOn("#username");
        robot.write("Admin");
        robot.clickOn("#password");
        robot.write("Admin10909090");
       // robot.clickOn("#login");
        //assertThat(robot.lookup("#mesaj").queryText()).hasText("Username or Password is incorrect!");
   }

}