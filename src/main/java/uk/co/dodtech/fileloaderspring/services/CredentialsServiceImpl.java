package uk.co.dodtech.fileloaderspring.services;

import org.springframework.stereotype.Service;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;

@Service
public class CredentialsServiceImpl implements CredentialsService {
    @Override
    public boolean checkCredentials(CredentialsDTO credentialsDTO) {
        boolean checkResult = true;

        // Check password is either sm_password or kgm_password
        if ( credentialsDTO.getPassword().compareTo(credentialsDTO.getUserType().toLowerCase() + "_password") != 0)
            checkResult = false;

        // Check Username has been changed from default in UI 'SM User' or 'KGM User'
        if ( credentialsDTO.getUsername().compareTo(credentialsDTO.getUserType() + " User" ) == 0 )
            checkResult = false;

        return checkResult;
    }
}
