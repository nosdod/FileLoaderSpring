package uk.co.dodtech.fileloaderspring.services;

import uk.co.dodtech.fileloaderspring.api.v1.mapper.EntropyMapper;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.domain.Entropy;
import uk.co.dodtech.fileloaderspring.repositories.EntropyRepository;
import org.springframework.mock.web.MockMultipartFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EntropyServiceImplTest {

    public static final Long BYTECOUNT = 2L;
    public static final String FILENAME = "entropy.epy";
    public static final String ENTROPY_8KB = "entropy_8KB.dat";
    public static final String ENTROPY_13KB = "entropy_13KB.dat";
    public static final String ENTROPY_20MB = "entropy_20MB.dat";

    EntropyService entropyService;
    File testFile;

    @Mock
    EntropyRepository entropyRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        entropyService = new EntropyServiceImpl(EntropyMapper.INSTANCE,entropyRepository);

        testFile = new File(FILENAME);
        testFile.createNewFile();
        try {
            FileWriter myWriter = new FileWriter(FILENAME);
            myWriter.write("OK");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        testFile.delete();
    }

    @Test
    public void getEntropyStatus() throws Exception {
        Entropy entropy = new Entropy();
        entropy.setFilename(FILENAME);

        when(entropyRepository.findById(1L)).thenReturn(java.util.Optional.of(entropy));

        //when
        EntropyDTO entropyDTO = entropyService.getEntropyStatus();

        //then
        assertEquals(BYTECOUNT, entropyDTO.getBytecount());
        assertEquals(FILENAME, entropyDTO.getFilename());
        assertEquals(EntropyService.STATUS_CRITICAL, entropyDTO.getStatus());
    }

    @Test
    public void uploadEntropy() throws Exception {
        Entropy entropy = new Entropy();
        entropy.setFilename(FILENAME);

        when(entropyRepository.findById(1L)).thenReturn(java.util.Optional.of(entropy));

        Long fileSize = 0L;

        ClassLoader classLoader = getClass().getClassLoader();
        File newEntropy = new File(classLoader.getResource(ENTROPY_20MB).getFile());

        FileInputStream inputFile = new FileInputStream( newEntropy.getAbsolutePath() );
        MockMultipartFile newEntropyFile = new MockMultipartFile("data", ENTROPY_20MB, "multipart/form", inputFile);

        //when
        entropyService.uploadEntropy(newEntropyFile);

        //then
        //assertEquals(fileSize, entropyDTO.getBytecount());
        //assertEquals(FILENAME, entropyDTO.getFilename());
        //assertEquals(EntropyService.STATUS_CRITICAL, entropyDTO.getStatus());
    }
}