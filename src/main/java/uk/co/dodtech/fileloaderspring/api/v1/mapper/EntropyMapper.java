package uk.co.dodtech.fileloaderspring.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.domain.Entropy;

@Mapper
public interface EntropyMapper {
    EntropyMapper INSTANCE = Mappers.getMapper(EntropyMapper.class);

    EntropyDTO entropyToEntropyDTO(Entropy entropy);
    Entropy entropyDTOToEntropy(EntropyDTO entropyDTO);
}
