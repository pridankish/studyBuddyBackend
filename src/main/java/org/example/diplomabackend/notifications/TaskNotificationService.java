package org.example.diplomabackend.notifications;

import org.example.diplomabackend.entity.Task;
import org.example.diplomabackend.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TaskNotificationService {

    private final EmailSender emailSender;
    private final TaskRepository taskRepository;

    public TaskNotificationService(EmailSender emailSender, TaskRepository taskRepository) {
        this.emailSender = emailSender;
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 60000 * 24)
    public void checkUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            LocalDateTime deadline = task.getDeadline();
            long minutesBeforeDeadline = ChronoUnit.MINUTES.between(deadline, now);

            if (minutesBeforeDeadline < 24 * 60) {
                sendNotification("Завтра наступает дедлайн для задания: "
                        + task.getName() + " по дисциплине " + task.getSubject().getName());
            }
        }
    }

    private void sendNotification(String message) {
        try {
            emailSender.sendEmail(
                    "pridankish101@gmail.com",
                    "StudyBuddy: Напоминание о дедлайне",
                    message
            );
        } catch (Exception e) {
            System.out.println("Ошибка отправки email: " + e.getMessage());
        }
    }
}
