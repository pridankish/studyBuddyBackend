package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.TaskRequestDTO;
import org.example.diplomabackend.controller.dto.response.TaskResponseDTO;
import org.example.diplomabackend.entity.Task;
import org.example.diplomabackend.service.SubjectService;
import org.example.diplomabackend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;
    private final SubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(
                taskService.getAll().stream()
                        .map(TaskResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public TaskResponseDTO createTask(
            @RequestBody TaskRequestDTO taskRequestDTO
    ) {
        var savedTask = taskService.addNew(
                new Task(
                        taskRequestDTO.getName(),
                        taskRequestDTO.getDeadline(),
                        taskRequestDTO.getDuration(),
                        taskRequestDTO.getIsCompleted(),
                        subjectService.getById(taskRequestDTO.getSubjectId())
                )
        );
        return new TaskResponseDTO(savedTask);
    }

    @PutMapping("/update/{id}")
    public TaskResponseDTO updateTask(
            @RequestBody TaskRequestDTO taskRequestDTO,
            @PathVariable Long id
    ) {
        var updatedTask = taskService.update(
                new Task(
                        taskRequestDTO.getName(),
                        taskRequestDTO.getDeadline(),
                        taskRequestDTO.getDuration(),
                        taskRequestDTO.getIsCompleted(),
                        subjectService.getById(taskRequestDTO.getSubjectId())
                ), id);
        return new TaskResponseDTO(updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }

    @GetMapping("/task/{id}")
    public TaskResponseDTO getTaskById(
            @PathVariable Long id
    ) {
        return new TaskResponseDTO(taskService.getById(id));
    }
}
