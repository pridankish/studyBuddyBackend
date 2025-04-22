package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.TaskRequestDTO;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime deadline;

    private Integer duration;

    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Task(String name, LocalDateTime deadline, Integer duration, Boolean isCompleted, Subject subject) {
        this.name = name;
        this.deadline = deadline;
        this.duration = duration;
        this.isCompleted = isCompleted;
        this.subject = subject;
    }

//    public Task(TaskRequestDTO taskRequestDTO) {
//        this.name = taskRequestDTO.getName();
//        this.deadline = taskRequestDTO.getDeadline();
//        this.duration = taskRequestDTO.getDuration();
//        this.isCompleted = taskRequestDTO.getIsCompleted();
//        this.subject = new Subject(taskRequestDTO.getSubject());
//    }
}