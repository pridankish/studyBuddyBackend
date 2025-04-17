package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.PersonalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalEventRepository extends JpaRepository<PersonalEvent, Long> {
}
