package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.University;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class UniversityResponseDTO {
    private Long id;

    private String universityName;

    private List<UserResponseDTO> users;

    private List<GroupResponseDTO> groups;

    public UniversityResponseDTO(University university) {
        this.id = university.getId();
        this.universityName = university.getUniversityName();
        this.users = university.getUsers().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
        this.groups = university.getGroups().stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList());
    }
}
