package uk.co.dodtech.fileloaderspring.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.dodtech.fileloaderspring.domain.Entropy;
import uk.co.dodtech.fileloaderspring.repositories.EntropyRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Bootstrap implements CommandLineRunner {
    private EntropyRepository entropyRepository;
    private static String DEFAULT_ENTROPY_FILE = "C:/UploadDir/entropy.dat";

    public Bootstrap(EntropyRepository entropyRepository) {
        this.entropyRepository = entropyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEntropy();
    }

    private void loadEntropy() {
        Entropy entropy = new Entropy();
        entropy.setFilename(DEFAULT_ENTROPY_FILE);

        Path directory = new File(entropy.getFilename()).toPath().getParent();

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

        Path entropyFile = new File(entropy.getFilename()).toPath();

        boolean fileExists = Files.exists(entropyFile);

        if ( ! fileExists ) {
            System.out.println("INFO : No entropy file found, creating " + entropyFile);
            try {
                Files.createFile(entropyFile);
            } catch ( Exception e ) {
                System.out.println("ERROR : Unable to create entropy file = " + entropyFile);
            }
        }

        entropyRepository.save(entropy);

        System.out.println("Entropy Data Loaded = " + entropyRepository.count() );
    }
}
