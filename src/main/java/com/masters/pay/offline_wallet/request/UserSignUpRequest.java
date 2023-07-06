package com.masters.pay.offline_wallet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserSignUpRequest {
    private String firstName;
    private String lastName;
    private String emailId;
    private String username;
    private String password;
    private String phoneNo;
}
