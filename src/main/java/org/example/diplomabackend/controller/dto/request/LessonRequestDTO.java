package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class LessonRequestDTO {

    private LocalDate lessonDate;

    private Long groupId;

    private Long subjectId;

    private String auditoryNumber;

    private Long lessonTypeId;

    private LocalTime startTime;

    private LocalTime endTime;
}
