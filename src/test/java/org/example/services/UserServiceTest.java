package org.example.services;

///import kotlin.internal.DynamicExtension;
import org.apache.commons.io.FileUtils;
import org.example.App;
import org.example.models.Produs;
import org.junit.jupiter.api.*;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.models.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    public static final String ADMIN = "admin";
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
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.removeUsers();
        UserService.closeDatabase();
        //SessionService.destroySession();
    }


    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () ->
        {
            UserService.addUser(ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN);
        });
    }
    @Test
    @DisplayName("Test remove all users from database")
    void testRemoveUsers() throws Exception{
        UserService.addUser(ADMIN,ADMIN,ADMIN);
        UserService.removeUsers();
        assertThat(UserService.getAllUsers()).isEmpty();

    }

    @Test
    @DisplayName("Test get all vendors")
    void testGetAllVanzatori() throws Exception{
        UserService.addUser("v1","v1","Vanzator");
        UserService.addUser("c1","c1","Client");
        assertThat(UserService.getVanzatori()).size().isEqualTo(1);

    }

    @Test
    @DisplayName("Test get last id of a product of a vendor")
    void testGetLastIdOfProduct() throws Exception{
        UserService.removeUsers();
        UserService.addUser("v1","v1","Vanzator");
        App.setUser(UserService.getUser("v1"));
        UserService.getUser("v1").setProduseNull();
        UserService.updateUser(App.getUser());
        assertThat(UserService.getLastIdOfProduct(App.getUser())).isEqualTo(-1);

        Produs p= new Produs(2.1,10.0,"Descriere","denumire");
        //Produs p1= new Produs(2.1,10.0,"Descriere1","denumire1");
        App.getUser().addProduse(p);
       // App.getUser().addProduse(p1);
        System.out.println(App.getUser().getProduse());
        UserService.updateUser(App.getUser());
        assertThat(UserService.getLastIdOfProduct(App.getUser())).isEqualTo(0);
        UserService.removeUsers();
    }

}