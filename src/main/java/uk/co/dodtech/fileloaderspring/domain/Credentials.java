package uk.co.dodtech.fileloaderspring.domain;

import lombok.Data;

@Data
public class Credentials {
    private String userType;
    private String username;
    private String password;
}
