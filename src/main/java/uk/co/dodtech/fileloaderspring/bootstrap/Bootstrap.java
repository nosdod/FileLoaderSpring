package uk.co.dodtech.fileloaderspring.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.dodtech.fileloaderspring.domain.Entropy;
import uk.co.dodtech.fileloaderspring.repositories.EntropyRepository;

@Component
public class Bootstrap implements CommandLineRunner {
    private EntropyRepository entropyRepository;

    public Bootstrap(EntropyRepository entropyRepository) {
        this.entropyRepository = entropyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEntropy();
    }

    private void loadEntropy() {
        Entropy entropy = new Entropy();
        entropy.setFilename("Z:/HPE Work/EntropyUploadDir/entropy.dat");

        entropyRepository.save(entropy);

        System.out.println("Entropy Data Loaded = " + entropyRepository.count() );
    }
}
