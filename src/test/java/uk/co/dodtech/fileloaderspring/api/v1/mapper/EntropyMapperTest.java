package uk.co.dodtech.fileloaderspring.api.v1.mapper;

import junit.framework.TestCase;
import org.junit.Test;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.domain.Entropy;

import static org.junit.Assert.assertEquals;

public class EntropyMapperTest extends TestCase {
    public static final String FILENAME = "entropy.epy";
    public static final long ID = 1L;

    EntropyMapper entropyMapper = EntropyMapper.INSTANCE;

    @Test
    public void testEntropyToEntropyDTO() {
        //given
        Entropy entropy = new Entropy();
        entropy.setFilename(FILENAME);
        entropy.setId(ID);

        //when
        EntropyDTO entropyDTO = entropyMapper.entropyToEntropyDTO(entropy);

        //then
        assertEquals(Long.valueOf(ID), entropyDTO.getId());
        assertEquals(FILENAME, entropyDTO.getFilename());
    }

    public void testEntropyDTOToEntropy() {
        //given
        EntropyDTO entropyDTO = new EntropyDTO();
        entropyDTO.setFilename(FILENAME);
        entropyDTO.setId(ID);

        //when
        Entropy entropy = entropyMapper.entropyDTOToEntropy(entropyDTO);

        //then
        assertEquals(Long.valueOf(ID), entropy.getId());
        assertEquals(FILENAME, entropy.getFilename());
    }
}