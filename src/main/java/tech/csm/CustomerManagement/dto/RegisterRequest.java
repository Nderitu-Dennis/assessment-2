package tech.csm.CustomerManagement.dto;

import lombok.Data;

@Data

public class RegisterRequest {
    private String userName;
    private String userPhone;
    private String userEmail;
    private String gender;
    private String password;
    private String photo;
}
