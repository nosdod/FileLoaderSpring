package uk.co.dodtech.fileloaderspring.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EntropyDTO {
    @ApiModelProperty( value = "The current size of the Entropy file", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Long bytecount;
    @ApiModelProperty( value = "The calculated status value", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String status; // ToDo : Convert to Enum

    @ApiModelProperty( value = "The location and name of the entropy file", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String filename;

    @ApiModelProperty( value = "The location and name of the entropy file", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String criticalSizeLimit;

    @ApiModelProperty( value = "The location and name of the entropy file", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String warningSizeLimit;
}
