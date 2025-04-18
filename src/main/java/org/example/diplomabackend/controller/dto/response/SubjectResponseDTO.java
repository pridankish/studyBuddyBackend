package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class SubjectResponseDTO {
    private Long id;

    private String name;

    private List<LessonResponseDTO> lessons;

    private List<TaskResponseDTO> tasks;

    public SubjectResponseDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.lessons = subject.getLessons().stream()
                .map(LessonResponseDTO::new)
                .collect(Collectors.toList());
        this.tasks = subject.getTasks().stream()
                .map(TaskResponseDTO::new)
                .collect(Collectors.toList());
    }
}
