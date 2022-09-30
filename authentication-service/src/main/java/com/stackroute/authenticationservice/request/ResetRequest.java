package com.stackroute.authenticationservice.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetRequest {

    private String emailId;
    @Size(min = 6, max = 12)
    private String password;
    @NotNull
    private String passwordConfirmation;
//    @AssertTrue(message = "password and confirm password are not same")
//    private boolean isPasswordConfirmation() {
//        return Objects.equals(password, passwordConfirmation);
//    }
}