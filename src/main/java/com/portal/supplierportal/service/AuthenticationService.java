package com.portal.supplierportal.service;

import com.portal.supplierportal.dto.*;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);

}
