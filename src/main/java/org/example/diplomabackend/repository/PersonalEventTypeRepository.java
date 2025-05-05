package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.PersonalEventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalEventTypeRepository extends JpaRepository<PersonalEventType, Long> {
}
