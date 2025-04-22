package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Group;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class GroupResponseDTO {

    private Long id;

    private String groupNumber;

    private List<UserResponseDTO> users;

    private Long universityId;

    private List<LessonResponseDTO> lessons;

    public GroupResponseDTO(Group group) {
        this.id = group.getId();
        this.groupNumber = group.getGroupNumber();
        this.users = group.getUsers().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
        this.universityId = group.getUniversity().getId();
        this.lessons = group.getLessons().stream()
                .map(LessonResponseDTO::new)
                .collect(Collectors.toList());
    }
}
