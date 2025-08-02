package com.portal.supplierportal.service;

import com.portal.supplierportal.dto.UserDTO;
import com.portal.supplierportal.dto.request.SignUpRequest;
import com.portal.supplierportal.dto.request.SigninRequest;
import com.portal.supplierportal.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    UserDTO atualizar(UserDTO dto);

    List<UserDTO> buscarTodos();

    List<UserDTO> buscarPorNome(String nome);

    UserDTO buscarPorId(Integer id);

    UserDTO getUsuarioLogado(String token);

    UserDTO buscarEntidadePorEmail(String email);

    Map<String, Object>  refreshToken(UserDetails userDetails);

}
