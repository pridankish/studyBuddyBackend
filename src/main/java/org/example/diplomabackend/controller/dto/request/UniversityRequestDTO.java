package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Group;
import org.example.diplomabackend.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UniversityRequestDTO {

    private String universityName;

    private List<User> users = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();
}
