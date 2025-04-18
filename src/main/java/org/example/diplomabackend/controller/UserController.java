package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.UserRequestDTO;
import org.example.diplomabackend.controller.dto.response.UserResponseDTO;
import org.example.diplomabackend.entity.User;
import org.example.diplomabackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(UserResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public UserResponseDTO createUser(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        var savedUser = userService.addNew(
                new User(
                        userRequestDTO.getFirstName(),
                        userRequestDTO.getLastName(),
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword(),
                        userRequestDTO.getCreatedAt(),
                        userRequestDTO.getGroup(),
                        userRequestDTO.getPersonalEvents(),
                        userRequestDTO.getUniversity()
                )
        );
        return new UserResponseDTO(savedUser);
    }

    @PutMapping("/update/{id}")
    public UserResponseDTO updateUser(
            @RequestBody UserRequestDTO userRequestDTO,
            @PathVariable Long id
    ) {
        var updatedUser = userService.update(
                new User(
                        userRequestDTO.getFirstName(),
                        userRequestDTO.getLastName(),
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword(),
                        userRequestDTO.getCreatedAt(),
                        userRequestDTO.getGroup(),
                        userRequestDTO.getPersonalEvents(),
                        userRequestDTO.getUniversity()
                ), id);
        return new UserResponseDTO(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

    @GetMapping("/user/{id}")
    public UserResponseDTO getUserById(
            @PathVariable Long id
    ) {
        return new UserResponseDTO(userService.getById(id));
    }
}
