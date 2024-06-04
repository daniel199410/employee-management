package dcatano;

import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;
import dcatano.infraestructure.presentation.Presentation;
import dcatano.infraestructure.presentation.console.Console;

public class Main {
    private static Presentation presentation = new Console();
    public static void main(String[] args) {
        InMemoryPersistence.initDB();
        presentation.execute();
    }
}