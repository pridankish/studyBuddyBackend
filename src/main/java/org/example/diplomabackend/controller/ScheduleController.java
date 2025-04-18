package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.ScheduleRequestDTO;
import org.example.diplomabackend.controller.dto.response.ScheduleResponseDTO;
import org.example.diplomabackend.entity.Schedule;
import org.example.diplomabackend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules() {
        return ResponseEntity.ok(
                scheduleService.getAll().stream()
                        .map(ScheduleResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ScheduleResponseDTO createSchedule(
            @RequestBody ScheduleRequestDTO scheduleRequestDTO
    ) {
        var savedSchedule = scheduleService.addNew(
                new Schedule(
                        scheduleRequestDTO.getGroup()
                )
        );
        return new ScheduleResponseDTO(savedSchedule);
    }

    @PutMapping("/update/{id}")
    public ScheduleResponseDTO updateSchedule(
            @RequestBody ScheduleRequestDTO scheduleRequestDTO,
            @PathVariable Long id
    ) {
        var updatedSchedule = scheduleService.update(
                new Schedule(
                        scheduleRequestDTO.getGroup()
                ), id);
        return new ScheduleResponseDTO(updatedSchedule);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSchedule(
            @PathVariable Long id
    ) {
        scheduleService.delete(id);
    }

    @GetMapping("/student/{id}")
    public ScheduleResponseDTO getStudentSchedule(
            @PathVariable Long id
    ) {
        return new ScheduleResponseDTO(scheduleService.getById(id));
    }
}
