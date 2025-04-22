package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Lesson;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class LessonResponseDTO {
    private Long id;

    private LocalDate lessonDate;

    private Long groupId;

    private Long subjectId;

    private String auditoryNumber;

    private String lessonType;

    private LocalTime startTime;

    private LocalTime endTime;

    public LessonResponseDTO(Lesson lesson) {
        this.id = lesson.getId();
        this.lessonDate = lesson.getLessonDate();
        this.groupId = lesson.getGroup().getId();
        this.subjectId = lesson.getSubject().getId();
        this.auditoryNumber = lesson.getAuditoryNumber();
        this.lessonType = lesson.getLessonType();
        this.startTime = lesson.getStartTime();
        this.endTime = lesson.getEndTime();
    }
}