package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Teacher;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class TeacherResponseDTO {
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private List<SubjectResponseDTO> subjects;

    public TeacherResponseDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.middleName = teacher.getMiddleName();
        this.lastName = teacher.getLastName();
        this.subjects = teacher.getSubjects().stream()
                .map(SubjectResponseDTO::new)
                .collect(Collectors.toList());
    }
}
