package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Task;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;

    private String name;

    private LocalDateTime deadline;

    private Integer duration;

    private Boolean isCompleted;

    private SubjectResponseDTO subject;

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.deadline = task.getDeadline();
        this.duration = task.getDuration();
        this.isCompleted = task.getIsCompleted();
        this.subject = new SubjectResponseDTO(task.getSubject());
    }
}
