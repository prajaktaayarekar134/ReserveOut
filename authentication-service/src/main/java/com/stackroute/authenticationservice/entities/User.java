package com.stackroute.authenticationservice.entities;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Auth")
public class User {

            @NotEmpty(message = "user Name should not be null or empty")
            private String userName;

            @Id
            @NotEmpty(message = "email Id should not be empty")
            @Email(message = "Invalid email id")
            private String emailId;

            @NotNull(message = "Role is mandatory it could be user or  restaurantOwner")
            @Enumerated(EnumType.STRING)
            private Role role;

// "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$"
    @Pattern(regexp = "^[a-zA-Z0-9]{8,12}",message = "Password should have at least 8 and maximum 12 character and password should have contain at least 1 digit , 1 uppercase and 1 lowercase character")
            private String password;


}
