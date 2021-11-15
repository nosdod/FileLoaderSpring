package uk.co.dodtech.fileloaderspring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;

import java.io.IOException;

@Service
public interface EntropyService {
    static final Long criticalSizeLimit = 5000L;
    static final Long warningSizeLimit = 20000L;
    static final String STATUS_CRITICAL = "CRITICAL";
    static final String STATUS_WARNING = "WARNING";
    static final String STATUS_NORMAL = "NORMAL";

    EntropyDTO getEntropyStatus();
    void uploadEntropy(MultipartFile uploadFile) throws IOException;
    //boolean checkCredentials( String usertype, String username, String password );

}
