package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
