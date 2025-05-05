package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.LessonType;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class LessonTypeResponseDTO {
    private Long id;

    private String name;

    private List<LessonResponseDTO> lessons;

    public LessonTypeResponseDTO(LessonType lessonType) {
        this.id = lessonType.getId();
        this.name = lessonType.getName();
        this.lessons = lessonType.getLessons().stream()
                .map(LessonResponseDTO::new)
                .collect(Collectors.toList());
    }
}
