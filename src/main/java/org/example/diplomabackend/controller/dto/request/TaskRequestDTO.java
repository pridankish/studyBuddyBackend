package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Subject;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequestDTO {

    private String name;

    private LocalDateTime deadline;

    private Integer duration;

    private Boolean isCompleted;

    private SubjectRequestDTO subject;
}
