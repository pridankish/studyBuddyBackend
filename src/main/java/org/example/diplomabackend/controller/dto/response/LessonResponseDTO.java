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

    private ScheduleResponseDTO schedule;

    private SubjectResponseDTO subject;

    private String auditoryNumber;

    private String lessonType;

    private LocalTime startTime;

    private LocalTime endTime;

    public LessonResponseDTO(Lesson lesson) {
        this.id = lesson.getId();
        this.lessonDate = lesson.getLessonDate();
        this.schedule = new ScheduleResponseDTO(lesson.getSchedule());
        this.subject = new SubjectResponseDTO(lesson.getSubject());
        this.auditoryNumber = lesson.getAuditoryNumber();
        this.lessonType = lesson.getLessonType();
        this.startTime = lesson.getStartTime();
        this.endTime = lesson.getEndTime();
    }
}
