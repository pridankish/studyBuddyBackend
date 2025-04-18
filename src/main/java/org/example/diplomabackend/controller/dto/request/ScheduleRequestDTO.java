package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Group;

@Getter
@Setter
public class ScheduleRequestDTO {

    private Group group;
}
