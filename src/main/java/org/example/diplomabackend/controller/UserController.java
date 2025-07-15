package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.UserRequestDTO;
import org.example.diplomabackend.controller.dto.response.UserResponseDTO;
import org.example.diplomabackend.entity.User;
import org.example.diplomabackend.exceptions.notFound.UserNotFoundException;
import org.example.diplomabackend.service.GroupService;
import org.example.diplomabackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(
        name = "Пользователи",
        description = "Методы для работы с пользователями"
)
public class UserController {

    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех пользователей")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(UserResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public UserResponseDTO createUser(
            @Parameter(description = "Информация о пользователе")
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        var userWithSameEmail = userService.findByEmail(userRequestDTO.getEmail()).isPresent();

        if (!userWithSameEmail) {
            var savedUser = userService.addNew(
                    new User(
                            userRequestDTO.getFirstName(),
                            userRequestDTO.getLastName(),
                            userRequestDTO.getEmail(),
                            userRequestDTO.getPassword(),
                            LocalDateTime.now(),
                            groupService.getById(userRequestDTO.getGroupId())
                    )
            );
            return new UserResponseDTO(savedUser);
        }
        else {
            throw new UserNotFoundException("User with email: " + userRequestDTO.getEmail() + " already exists");
        }
    }

//    @PutMapping("/update/{id}")
//    public UserResponseDTO updateUser(
//            @RequestBody UserRequestDTO userRequestDTO,
//            @PathVariable Long id
//    ) {
//        var updatedUser = userService.update(
//                new User(
//                        userRequestDTO.getFirstName(),
//                        userRequestDTO.getLastName(),
//                        userRequestDTO.getEmail(),
//                        userRequestDTO.getPassword(),
//                        userRequestDTO.getCreatedAt(),
//                        new Group(userRequestDTO.getGroup()),
//                        userRequestDTO.getPersonalEvents().stream()
//                                .map(PersonalEvent::new)
//                                .collect(Collectors.toList()),
//                        new University(userRequestDTO.getUniversity())
//                ), id);
//        return new UserResponseDTO(updatedUser);
//    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить пользователя по его id")
    public void deleteUser(
            @Parameter(description = "id пользователя")
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Получить пользователя по его id")
    public UserResponseDTO getUserById(
            @Parameter(description = "id пользователя")
            @PathVariable Long id
    ) {
        return new UserResponseDTO(userService.getById(id));
    }
}
