package org.example.diplomabackend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.diplomabackend.config.utils.JwtUtil;
import org.example.diplomabackend.controller.dto.request.UserRequestDTO;
import org.example.diplomabackend.entity.User;
import org.example.diplomabackend.service.GroupService;
import org.example.diplomabackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final GroupService groupService;

    @PostMapping("/login")
    public TokenResponse login(
            @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new TokenResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        if (userService.register(
                new User(
                        userRequestDTO.getFirstName(),
                        userRequestDTO.getLastName(),
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword(),
                        LocalDateTime.now(),
                        groupService.getById(userRequestDTO.getGroupId())
                )
        )) {
            return ResponseEntity.ok("User registered successfully");
        }
        else {
            return ResponseEntity.badRequest().body("User with email: " + userRequestDTO.getEmail() + " already exists");
        }
    }

    @Getter
    @Setter
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    static class TokenResponse {
        private String token;
    }
}
