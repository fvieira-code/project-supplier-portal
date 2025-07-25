package com.portal.supplierportal.service;

import com.portal.supplierportal.dto.UserDTO;
import com.portal.supplierportal.dto.request.SignUpRequest;
import com.portal.supplierportal.dto.request.SigninRequest;
import com.portal.supplierportal.dto.response.JwtAuthenticationResponse;

import java.util.List;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);

    UserDTO atualizar(UserDTO dto);
    List<UserDTO> buscarTodos();
    List<UserDTO> buscarPorNome(String nome);

}
