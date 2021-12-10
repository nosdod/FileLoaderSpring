package uk.co.dodtech.fileloaderspring.controllers.v1;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WhoamiControllerTest extends TestCase {

    MockMvc mockMvc;

    @InjectMocks
    WhoamiController whoamiController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(whoamiController).build();
    }

    @Test
    public void testGetWhoami() throws Exception {

            String expectedResponse = "Java Spring5 server";

            mockMvc.perform(get(WhoamiController.BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message", equalTo(expectedResponse)));
    }
}