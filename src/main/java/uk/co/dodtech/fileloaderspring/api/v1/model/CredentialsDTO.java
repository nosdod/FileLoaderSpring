package uk.co.dodtech.fileloaderspring.api.v1.model;

import lombok.Data;

@Data
public class CredentialsDTO {
    private String userType;
    private String username;
    private String password;
}
