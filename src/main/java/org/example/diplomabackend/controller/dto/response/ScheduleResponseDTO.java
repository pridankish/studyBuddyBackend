package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Schedule;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDTO {
    private Long id;

    private Long groupId;

    public ScheduleResponseDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.groupId = schedule.getGroup().getId();
    }
}
