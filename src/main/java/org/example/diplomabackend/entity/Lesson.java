package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "lesson_type_id")
    private LessonType lessonType;

    private String auditoryNumber;

    private LocalTime startTime;

    private LocalTime endTime;

    public Lesson(LocalDate lessonDate, Group group, Subject subject, String auditoryNumber, LessonType lessonType, LocalTime startTime, LocalTime endTime) {
        this.lessonDate = lessonDate;
        this.group = group;
        this.subject = subject;
        this.auditoryNumber = auditoryNumber;
        this.lessonType = lessonType;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}