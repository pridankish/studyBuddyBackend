package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private GroupResponseDTO group;

    private List<PersonalEventResponseDTO> personalEvents;

    private UniversityResponseDTO university;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        this.group = new GroupResponseDTO(user.getGroup());
        this.personalEvents = user.getPersonalEvents().stream()
                .map(PersonalEventResponseDTO::new)
                .collect(Collectors.toList());

        this.university = new UniversityResponseDTO(user.getUniversity());
    }
}
