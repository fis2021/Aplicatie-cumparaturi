package org.example.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.example.App;
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
import java.lang.IndexOutOfBoundsException;

import java.io.IOException;

import static org.example.App.loadFXML;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class StergereProduseControllerTest {

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
    public void start(Stage stage) throws Exception {
        //FileSystemService.initDirectory();
        UserService.initDatabase();
        ComandaService.initDatabase();
        UserService.addUser("V1","v1","Vanzator");
        App.setUser(UserService.getUser("V1"));
        App.getUser().setProduseNull();
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(1.0,200,"Mere romanesti ","Mere Golden"));
        UserService.updateUser(App.getUser());
        App.getUser().addProduse(new Produs(2.0,200,"Mere romanesti ","Mere Ionatan"));
        UserService.updateUser(App.getUser());
        scene = new Scene(loadFXML("StergereProduse.fxml"), 950,700);
        stage.setTitle("Aplicatie Cumparaturi -test");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Verify StergereProduse tab is displayed")
    void testDysplayStergereProduse(FxRobot robot){

    }

    @Test
    @DisplayName("Verify cautaProdus successfully scenario")
    void testCauta(FxRobot robot){
        App.getUser().addProduse(new Produs(2.0,200,"Mere romanesti ","Mere Ionatan"));
        UserService.updateUser(App.getUser());
        robot.clickOn("#idProdus").write("1");
        robot.clickOn("#cautaButton");
        assertThat(robot.lookup("#mesaj1").queryText()).hasText("Produs gasit!");
        /*robot.clickOn("#stergeButton");
        assertThat(robot.lookup("#mesaj2").queryText()).hasText("Produs sters cu succes!");
      //  assertThat(UserService.getUser(App.getUser().getUsername()).getProduse().get(2)).isNull();


         assertThrows(IndexOutOfBoundsException.class, () ->
        {
            UserService.getUser(App.getUser().getUsername()).getProduse().get(2);
        });
*/

    }
    @Test
    @DisplayName("Verify stergeProdus successfully scenario")
    void testSterge(FxRobot robot){
       /* App.getUser().addProduse(new Produs(1.0,200,"Mere romanesti ","Mere Golden"));
        App.getUser().addProduse(new Produs(2.0,200,"Mere romanesti ","Mere Ionatan"));*/
        UserService.updateUser(App.getUser());
       // UserService.updateUser(App.getUser());
        robot.clickOn("#idProdus").write("1");
        robot.clickOn("#cautaButton");
        assertThat(robot.lookup("#mesaj1").queryText()).hasText("Produs gasit!");
        robot.clickOn("#stergeButton");
        assertThat(robot.lookup("#mesaj2").queryText()).hasText("Produs sters cu succes!");
        UserService.updateUser(App.getUser());
      //  assertThat(UserService.getUser(App.getUser().getUsername()).getProduse().get(2)).isNull();


         assertThat(App.getUser().getProduse().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Verify with an incorrect id of product")
    void testCautaIncorrect1(FxRobot robot){
        robot.clickOn("#idProdus").write("3");
        robot.clickOn("#cautaButton");
        //assertThat(robot.lookup("#mesaj1").queryText()).hasText("Produs gasit!");
        assertThat(robot.lookup("#mesaj1").queryText()).hasText("Id incorect!");
    }

    @Test
    @DisplayName("Verify with an incorrect by not choosing of an id of product")
    void testCautaIncorrect2(FxRobot robot){
        robot.clickOn("#stergeButton");
        assertThat(robot.lookup("#mesaj2").queryText()).hasText("Introduceti id-ul produsului dorit!");
    }


}