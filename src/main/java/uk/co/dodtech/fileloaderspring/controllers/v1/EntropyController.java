package uk.co.dodtech.fileloaderspring.controllers.v1;


import uk.co.dodtech.fileloaderspring.api.v1.model.EntropyDTO;
import uk.co.dodtech.fileloaderspring.services.EntropyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(value = "Entropy Status")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(EntropyController.BASE_URL)
public class EntropyController {
    public static final String BASE_URL = "/api/v1/entropy";
    private final EntropyService entropyService;

    public EntropyController(EntropyService entropyService) {
        this.entropyService = entropyService;
    }

    @ApiOperation( value = "This will return the Entropy file information")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntropyDTO getEntropyStatus() {
        return entropyService.getEntropyStatus();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadEntropy(@RequestParam(value = "data") MultipartFile uploadFile) throws IOException {
        entropyService.uploadEntropy(uploadFile);
    }

}
