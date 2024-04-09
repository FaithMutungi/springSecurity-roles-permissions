package com.preciousCode.SpringSecurityJwt.auth;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
