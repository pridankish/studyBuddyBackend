package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleRequestDTO {

    private GroupRequestDTO group;

    private List<LessonRequestDTO> lessons;
}
