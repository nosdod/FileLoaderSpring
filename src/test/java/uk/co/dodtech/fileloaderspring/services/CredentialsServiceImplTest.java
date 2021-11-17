package uk.co.dodtech.fileloaderspring.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;

import java.util.Locale;

import static org.junit.Assert.*;

public class CredentialsServiceImplTest {
    private final String SM_USER_TYPE = "SM";
    private final String USERNAME = SM_USER_TYPE + " User";
    private final String GOOD_USERNAME = USERNAME + " Test";
    private final String BAD_PASSWORD = "bad_password";
    private final String GOOD_PASSWORD = SM_USER_TYPE.toLowerCase(Locale.ROOT) + "_password";

    CredentialsService credentialsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        credentialsService = new CredentialsServiceImpl();
    }

    @Test
    public void testCheckGoodCredentials() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUserType(SM_USER_TYPE);
        credentialsDTO.setUsername(GOOD_USERNAME);
        credentialsDTO.setPassword(GOOD_PASSWORD);

        //when
        boolean checkResult = credentialsService.checkCredentials(credentialsDTO);

        //then
        assertEquals(checkResult, true);
    }

    @Test
    public void testCheckBadUsernameCredentials() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUserType(SM_USER_TYPE);
        credentialsDTO.setUsername(USERNAME);
        credentialsDTO.setPassword(GOOD_PASSWORD);

        //when
        boolean checkResult = credentialsService.checkCredentials(credentialsDTO);

        //then
        assertEquals(checkResult, false);
    }

    @Test
    public void testCheckBadPasswordCredentials() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUserType(SM_USER_TYPE);
        credentialsDTO.setUsername(GOOD_USERNAME);
        credentialsDTO.setPassword(BAD_PASSWORD);

        //when
        boolean checkResult = credentialsService.checkCredentials(credentialsDTO);

        //then
        assertEquals(checkResult, false);
    }

}