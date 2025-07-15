package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Учебные задания",
        description = "Методы для работы с учебными заданиями"
)
public class TaskController {

    private final TaskService taskService;
    private final SubjectService subjectService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех учебных заданий")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(
                taskService.getAll().stream()
                        .map(TaskResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать учебное задание")
    public TaskResponseDTO createTask(
            @Parameter(description = "Информация об учебном задании")
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
    @Operation(summary = "Изменить учебное задание по id")
    public TaskResponseDTO updateTask(
            @Parameter(description = "Информация об учебном задании")
            @RequestBody TaskRequestDTO taskRequestDTO,

            @Parameter(description = "id учебного задания")
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
    @Operation(summary = "Изменить учебное задание по id")
    public void deleteTask(
            @Parameter(description = "id учебного задания")
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }

    @GetMapping("/task/{id}")
    @Operation(summary = "Удалить учебное задание по id")
    public TaskResponseDTO getTaskById(
            @Parameter(description = "id учебного задания")
            @PathVariable Long id
    ) {
        return new TaskResponseDTO(taskService.getById(id));
    }
}
