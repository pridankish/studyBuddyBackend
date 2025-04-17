package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
