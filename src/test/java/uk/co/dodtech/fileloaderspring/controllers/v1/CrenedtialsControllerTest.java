package uk.co.dodtech.fileloaderspring.controllers.v1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;
import uk.co.dodtech.fileloaderspring.services.CredentialsService;

import java.util.Locale;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CrenedtialsControllerTest extends AbstractControllerTest {

    private final String SM_USER_TYPE = "SM";
    private final String USERNAME = SM_USER_TYPE + " User";
    private final String GOOD_USERNAME = USERNAME + " Test";
    private final String BAD_PASSWORD = "bad_password";
    private final String GOOD_PASSWORD = SM_USER_TYPE.toLowerCase(Locale.ROOT) + "_password";

    @Mock
    CredentialsService credentialsService;

    @InjectMocks
    CredentialsController credentialsController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(credentialsController).build();
    }

    @Test
    public void testCheckGoodCredentials() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUserType(SM_USER_TYPE);
        credentialsDTO.setUsername(GOOD_USERNAME);
        credentialsDTO.setPassword(GOOD_PASSWORD);

        when(credentialsService.checkCredentials(any(CredentialsDTO.class))).thenReturn(true);

        mockMvc.perform(post(CredentialsController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userOK", equalTo(true)));
    }

    @Test
    public void testCheckBadCredentials() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUserType(SM_USER_TYPE);
        credentialsDTO.setUsername(USERNAME);
        credentialsDTO.setPassword(BAD_PASSWORD);

        when(credentialsService.checkCredentials(any(CredentialsDTO.class))).thenReturn(false);

        mockMvc.perform(post(CredentialsController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userOK", equalTo(false)));
    }
}

