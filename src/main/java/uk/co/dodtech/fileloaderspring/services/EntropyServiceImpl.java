package uk.co.dodtech.fileloaderspring.services;

import org.springframework.beans.factory.annotation.Value;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EntropyServiceImpl implements EntropyService {
    private final Long criticalSizeLimit;
    private final Long warningSizeLimit;
    private final String entropyFilePath;

    public EntropyServiceImpl(@Value("${criticalSizeLimit}") Long criticalSizeLimit,
                              @Value("${warningSizeLimit}") Long warningSizeLimit,
                              @Value("${entropyFilePath}") String entropyFilePath) {
        this.criticalSizeLimit = criticalSizeLimit;
        this.warningSizeLimit = warningSizeLimit;
        this.entropyFilePath = entropyFilePath;
    }

    public Long getCriticalSizeLimit() {
        return criticalSizeLimit;
    }

    public Long getWarningSizeLimit() {
        return warningSizeLimit;
    }

    public String getEntropyFilePath() {
        return entropyFilePath;
    }

    private String getStatus(Long fileSizeBytes) {
        if ( fileSizeBytes <= criticalSizeLimit ) {
            return STATUS_CRITICAL;
        } else if ( fileSizeBytes <= warningSizeLimit ) {
            return STATUS_WARNING;
        } else {
            return STATUS_NORMAL;
        }
    }

    @Override
    public EntropyDTO getEntropyStatus() {
        EntropyDTO entropyDTO = new EntropyDTO();

        Long fileSize = 0L;
        try {
            fileSize = Files.size(new File(entropyFilePath).toPath());
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
        entropyDTO.setBytecount( fileSize );
        entropyDTO.setStatus( getStatus(fileSize));
        entropyDTO.setCriticalSizeLimit(getCriticalSizeLimit().toString());
        entropyDTO.setWarningSizeLimit(getWarningSizeLimit().toString());
        entropyDTO.setFilename(getEntropyFilePath());
        return entropyDTO;
    }

    @Override
    public void uploadEntropy(MultipartFile uploadFile) throws IOException {
        uploadFile.transferTo(Path.of(entropyFilePath));
    }
}
