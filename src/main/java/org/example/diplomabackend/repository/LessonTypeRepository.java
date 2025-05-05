package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {
}
