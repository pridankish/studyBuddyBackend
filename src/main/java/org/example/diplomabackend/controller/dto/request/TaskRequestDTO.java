package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequestDTO {

    private String name;

    private LocalDateTime deadline;

    private Integer duration;

    private Boolean isCompleted;

    private Long subjectId;
}
