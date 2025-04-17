package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Lesson;
import org.example.diplomabackend.exceptions.notFound.LessonNotFoundException;
import org.example.diplomabackend.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService implements IService<Lesson, Long> {

    private final LessonRepository lessonRepository;

    @Override
    public Lesson addNew(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson with id: " + id + " doesn't exist"));
    }

    @Override
    public Lesson update(Lesson lesson, Long id) {
        return lessonRepository.findById(id)
                .map(
                        les -> {
                            les.setLessonDate(lesson.getLessonDate());
                            les.setSchedule(lesson.getSchedule());
                            les.setSubject(lesson.getSubject());
                            les.setAuditoryNumber(lesson.getAuditoryNumber());
                            les.setLessonType(lesson.getLessonType());
                            les.setStartTime(lesson.getStartTime());
                            les.setEndTime(lesson.getEndTime());
                            return lessonRepository.save(les);
                        }
                )
                .orElseThrow(() -> new LessonNotFoundException("Lesson with id: " + id + " doesn't exist"));
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new LessonNotFoundException("Lesson with id: " + id + " doesn't exist");
        }
        lessonRepository.deleteById(id);
    }
}