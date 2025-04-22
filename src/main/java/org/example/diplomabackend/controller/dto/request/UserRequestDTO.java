package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long groupId;

    private Long universityId;
}