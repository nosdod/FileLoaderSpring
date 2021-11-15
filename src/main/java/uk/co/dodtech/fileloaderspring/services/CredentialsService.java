package uk.co.dodtech.fileloaderspring.services;

import org.springframework.stereotype.Service;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;

@Service
public interface CredentialsService {
    boolean checkCredentials(CredentialsDTO credentialsDTO);
}
