package uk.co.dodtech.fileloaderspring.services;

import uk.co.dodtech.fileloaderspring.api.v1.mapper.EntropyMapper;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.domain.Entropy;
import uk.co.dodtech.fileloaderspring.repositories.EntropyRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntropyServiceImpl implements EntropyService {

    private final EntropyMapper entropyMapper;
    private final EntropyRepository entropyRepository;

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
        return entropyRepository.findById(1L)
                .map(entropy -> {
                    EntropyDTO entropyDTO = entropyMapper.entropyToEntropyDTO(entropy);
                    try {
                        Long fileSize = Files.size(new File(entropy.getFilename()).toPath());
                        entropyDTO.setBytecount( fileSize );
                        entropyDTO.setStatus( getStatus(fileSize));
                        return entropyDTO;
                    } catch (Exception e) {
                        throw new ResourceNotFoundException(e);
                    }
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void uploadEntropy(MultipartFile uploadFile) throws IOException {
        Long fileSize = 0L;

        String entropyFilename = entropyRepository.findById(1L)
                .map(entropy -> {
                    return entropy.getFilename();
                })
                .orElseThrow(ResourceNotFoundException::new);

        uploadFile.transferTo(Path.of(entropyFilename));
    }

    // @Override
    // public boolean checkCredentials(String usertype, String username, String password) {
    //    if ( usertype. "SM" ) {
//
//        }
//        return false;
//    }
}
