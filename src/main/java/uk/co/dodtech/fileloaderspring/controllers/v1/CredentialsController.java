package uk.co.dodtech.fileloaderspring.controllers.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.co.dodtech.fileloaderspring.api.v1.model.CredentialsDTO;
import uk.co.dodtech.fileloaderspring.services.CredentialsService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Api(value = "Credentials Checker")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(CredentialsController.BASE_URL)
public class CredentialsController {
    public static final String BASE_URL = "/api/v1/credentials";
    private final CredentialsService credentialsService;

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String checkCredentials(@RequestBody CredentialsDTO credentialsDTO) throws IOException {
        boolean userOK = credentialsService.checkCredentials(credentialsDTO);
            Map<String, Object> params = new HashMap<>();
            params.put("userOK", userOK);
            String payload = new ObjectMapper().writeValueAsString(params);
            return payload;
    }
}
