package uk.co.dodtech.fileloaderspring.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EntropyDTO {
    private Long id;

    @ApiModelProperty( value = "The current size of the Entropy file", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Long bytecount;
    @ApiModelProperty( value = "The calculated status value", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String status; // ToDo : Convert to Enum

    @ApiModelProperty( value = "The location and name of the entropy file", required = true)
    private String filename;
}
