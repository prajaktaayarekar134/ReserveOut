package com.stackroute.authenticationservice.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    private String userName;
    private String password;
}
