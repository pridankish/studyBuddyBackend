package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> getLessonsByLessonDate(LocalDate lessonDate);
}