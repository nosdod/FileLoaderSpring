package uk.co.dodtech.fileloaderspring.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;
import uk.co.dodtech.fileloaderspring.domain.Credentials;

@Mapper
public interface CredentialsMapper {
    CredentialsMapper INSTANCE = Mappers.getMapper(CredentialsMapper.class);

    CredentialsDTO credentialsToCredentialsDTO(Credentials credentials);
    Credentials credentialsDTOToCredentials(CredentialsDTO credentialsDTO);
}
