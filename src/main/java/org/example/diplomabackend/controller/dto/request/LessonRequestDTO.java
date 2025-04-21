package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Subject;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class LessonRequestDTO {

    private LocalDate lessonDate;

    private ScheduleRequestDTO schedule;

    private Subject subject;

    private String auditoryNumber;

    private String lessonType;

    private LocalTime startTime;

    private LocalTime endTime;
}
