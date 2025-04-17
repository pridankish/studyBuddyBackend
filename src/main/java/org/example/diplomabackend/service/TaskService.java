package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Task;
import org.example.diplomabackend.exceptions.notFound.TaskNotFoundException;
import org.example.diplomabackend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements IService<Task, Long>{

    private final TaskRepository taskRepository;

    @Override
    public Task addNew(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " doesn't exist"));
    }

    @Override
    public Task update(Task task, Long id) {
        return taskRepository.findById(id)
                .map(
                        t -> {
                            t.setName(task.getName());
                            t.setDeadline(task.getDeadline());
                            t.setDuration(task.getDuration());
                            t.setIsCompleted(task.getIsCompleted());
                            t.setSubject(task.getSubject());
                            return taskRepository.save(t);
                        }
                )
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " doesn't exist"));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with id " + id + " doesn't exist");
        }
        taskRepository.deleteById(id);
    }
}
