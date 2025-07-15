package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.config.security.utils.JwtUtil;
import org.example.diplomabackend.controller.dto.request.LoginRequest;
import org.example.diplomabackend.controller.dto.request.UserRequestDTO;
import org.example.diplomabackend.controller.dto.response.TokenResponse;
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
@Tag(
        name = "Регистрация и логин",
        description = "Методы для регистрации и логина"
)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final GroupService groupService;

    @PostMapping("/login")
    @Operation(summary = "Логин пользователя")
    public TokenResponse login(
            @Parameter(description = "Информация о пользователе (имя пользователя и пароль)")
            @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new TokenResponse(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<String> register(
            @Parameter(description = "Инормация о пользователе")
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
}
