package org.example.diplomabackend.repository;

import org.example.diplomabackend.entity.PersonalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonalEventRepository extends JpaRepository<PersonalEvent, Long> {
    Optional<List<PersonalEvent>> getPersonalEventByEventDate(LocalDate date);
}
