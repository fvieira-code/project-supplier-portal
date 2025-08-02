package com.portal.supplierportal.controller;

import com.portal.supplierportal.dto.UserDTO;
import com.portal.supplierportal.dto.request.SignUpRequest;
import com.portal.supplierportal.dto.request.SigninRequest;
import com.portal.supplierportal.dto.response.JwtAuthenticationResponse;
import com.portal.supplierportal.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(authenticationService.atualizar(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> users(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(authenticationService.buscarPorNome(nome));
        }
        return ResponseEntity.ok(authenticationService.buscarTodos());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(authenticationService.buscarPorId(id));
    }

    @GetMapping("/inToken")
    public ResponseEntity<UserDTO> getUsuarioLogado(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authenticationService.getUsuarioLogado(token));
    }

    @GetMapping("/inDetails")
    public ResponseEntity<UserDTO> getUsuarioLogado(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.buscarEntidadePorEmail(userDetails.getUsername()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.refreshToken(userDetails));
    }
}
