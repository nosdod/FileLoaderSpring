package uk.co.dodtech.fileloaderspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.dodtech.fileloaderspring.domain.Entropy;

public interface EntropyRepository extends JpaRepository<Entropy, Long> {
}
