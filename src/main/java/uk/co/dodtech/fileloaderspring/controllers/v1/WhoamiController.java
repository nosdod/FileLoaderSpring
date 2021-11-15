package uk.co.dodtech.fileloaderspring.controllers.v1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "Who am i")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(WhoamiController.BASE_URL)
public class WhoamiController {
    public static final String BASE_URL = "/api/v1/whoami";

    @ApiOperation( value = "This will return a string identifying the server")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getWhoami() throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();
        params.put("message", "Java Spring5 server");
        String payload = new ObjectMapper().writeValueAsString(params);

        return payload;
    }
}
