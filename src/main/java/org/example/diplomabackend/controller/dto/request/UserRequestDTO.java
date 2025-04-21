package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private GroupRequestDTO group;

    private List<PersonalEventRequestDTO> personalEvents;

    private UniversityRequestDTO university;
}
