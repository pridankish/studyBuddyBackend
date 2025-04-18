package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Group;
import org.example.diplomabackend.entity.PersonalEvent;
import org.example.diplomabackend.entity.University;

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

    private Group group;

    private List<PersonalEvent> personalEvents;

    private University university;
}
