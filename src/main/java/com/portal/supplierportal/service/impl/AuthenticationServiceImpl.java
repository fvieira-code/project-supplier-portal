package com.portal.supplierportal.service.impl;


import com.portal.supplierportal.dto.UserDTO;
import com.portal.supplierportal.dto.response.JwtAuthenticationResponse;
import com.portal.supplierportal.dto.request.SignUpRequest;
import com.portal.supplierportal.dto.request.SigninRequest;
import com.portal.supplierportal.exception.RecursoNaoEncontradoException;
import com.portal.supplierportal.model.Role;
import com.portal.supplierportal.model.User;
import com.portal.supplierportal.repository.UserRepository;
import com.portal.supplierportal.service.AuthenticationService;
import com.portal.supplierportal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public UserDTO atualizar(UserDTO dto) {
        User entity = userRepository.findById(Math.toIntExact(dto.getId()))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        userRepository.save(entity);
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getRole());
    }

    @Override
    public List<UserDTO> buscarTodos() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserDTO(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> buscarPorNome(String nome) {
        return userRepository.findByFirstNameContainingIgnoreCase(nome)
                .stream()
                .map(u -> new UserDTO(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getRole()))
                .collect(Collectors.toList());
    }
}
