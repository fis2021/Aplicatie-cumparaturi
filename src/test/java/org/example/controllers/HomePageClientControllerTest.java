package org.example.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.App;
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

@ExtendWith(ApplicationExtension.class)
class HomePageClientControllerTest {
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
        scene = new Scene(loadFXML("HomePageClient.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify HomePage for client is displayed.")
    void testDemo1(FxRobot robot) throws Exception{


    }


}