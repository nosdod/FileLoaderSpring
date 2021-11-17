package uk.co.dodtech.fileloaderspring.bootstrap;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.dodtech.fileloaderspring.services.EntropyService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Bootstrap implements CommandLineRunner {
    private String entropyFilePath;

    public Bootstrap(@Value("${entropyFilePath}") String entropyFilePath) {
        this.entropyFilePath = entropyFilePath;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEntropy();
    }

    private void loadEntropy() {
        Path directory = new File(entropyFilePath).toPath().getParent();

        // Check the server has been setup correctly
        boolean directoryExists = Files.exists(directory);

        if ( ! directoryExists ) {
            System.out.println("INFO : No directory for entropy file found, creating " + directory);
            try {
                Files.createDirectory(directory);
            } catch ( Exception e ) {
                System.out.println("ERROR : Unable to create directory = " + directory);
            }
        }

        Path entropyFile = new File(entropyFilePath).toPath();

        boolean fileExists = Files.exists(entropyFile);

        if ( ! fileExists ) {
            System.out.println("INFO : No entropy file found, creating " + entropyFile);
            try {
                Files.createFile(entropyFile);
            } catch ( Exception e ) {
                System.out.println("ERROR : Unable to create entropy file = " + entropyFile);
            }
        }

        System.out.println("Entropy Data Checks complete");
    }
}
