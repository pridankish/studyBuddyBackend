package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.LessonRequestDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate lessonDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String auditoryNumber;

    private String lessonType;

    private LocalTime startTime;

    private LocalTime endTime;

    public Lesson(LocalDate lessonDate, Schedule schedule, String auditoryNumber, String lessonType, LocalTime startTime, LocalTime endTime) {
        this.lessonDate = lessonDate;
        this.schedule = schedule;
        this.auditoryNumber = auditoryNumber;
        this.lessonType = lessonType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Lesson(LessonRequestDTO lessonRequestDTO) {
        this.lessonDate = lessonRequestDTO.getLessonDate();
        this.schedule = new Schedule(lessonRequestDTO.getSchedule());
        this.auditoryNumber = lessonRequestDTO.getAuditoryNumber();
        this.lessonType = lessonRequestDTO.getLessonType();
        this.startTime = lessonRequestDTO.getStartTime();
        this.endTime = lessonRequestDTO.getEndTime();
    }
}