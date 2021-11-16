package uk.co.dodtech.fileloaderspring.controllers.v1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.services.EntropyService;
import uk.co.dodtech.fileloaderspring.controllers.RestResponseEntityExceptionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntropyControllerTest {

    public static final Long ID = 1L;
    public static final String FILENAME = "entropy.epy";
    public static final String ENTROPY_8KB = "entropy_8KB.dat";
    public static final String ENTROPY_13KB = "entropy_13KB.dat";
    public static final String ENTROPY_20MB = "entropy_20MB.dat";
    public static final Long BYTECOUNT = 2L;

    File testFile;

    @Mock
    EntropyService entropyService;

    @InjectMocks
    EntropyController entropyController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(entropyController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

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
    public void testGetEntropyStatus() throws Exception {
        EntropyDTO entropyDTO = new EntropyDTO();
        entropyDTO.setFilename(FILENAME);
        entropyDTO.setStatus(EntropyService.STATUS_CRITICAL);
        entropyDTO.setBytecount(BYTECOUNT);

        when(entropyService.getEntropyStatus()).thenReturn(entropyDTO);

        mockMvc.perform(get(EntropyController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filename", equalTo(FILENAME)))
                .andExpect(jsonPath("$.status", equalTo(EntropyService.STATUS_CRITICAL)));
    }

    @Test
    public void testUploadEntropy() throws Exception {
        EntropyDTO entropyDTO = new EntropyDTO();
        entropyDTO.setFilename(FILENAME);
        entropyDTO.setStatus(EntropyService.STATUS_CRITICAL);
        entropyDTO.setBytecount(BYTECOUNT);

        when(entropyService.getEntropyStatus()).thenReturn(entropyDTO);

        Long fileSize = 0L;

        ClassLoader classLoader = getClass().getClassLoader();
        File newEntropy = new File(classLoader.getResource(ENTROPY_20MB).getFile());

        FileInputStream inputFile = new FileInputStream( newEntropy.getAbsolutePath() );
        MockMultipartFile newEntropyFile = new MockMultipartFile("data", ENTROPY_20MB, "multipart/form", inputFile);

        mockMvc.perform(multipart(EntropyController.BASE_URL)
                        .file(newEntropyFile))
                .andExpect(status().isCreated());
    }}