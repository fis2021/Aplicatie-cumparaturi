package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = "Aplicatie-cumparaturi";
    private static final String USER_FOLDER = System.getProperty("C:\\Users\\ghine\\Desktop\\Facultate\\FIS\\AplicatieV2\\");
    public static final Path APPLICATION_HOME_PATH = Paths.get("C:\\Users\\ghine\\Desktop\\Facultate\\FIS\\AplicatieV2\\Aplicatie-cumparaturi");

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
}
