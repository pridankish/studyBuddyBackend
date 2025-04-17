package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.PersonalEvent;
import org.example.diplomabackend.exceptions.notFound.PersonalEventNotFoundException;
import org.example.diplomabackend.repository.PersonalEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalEventsService implements IService<PersonalEvent, Long> {

    private final PersonalEventRepository personalEventRepository;

    @Override
    public PersonalEvent addNew(PersonalEvent personalEvent) {
        return personalEventRepository.save(personalEvent);
    }

    @Override
    public PersonalEvent getById(Long id) {
        return personalEventRepository.findById(id)
                .orElseThrow(() -> new PersonalEventNotFoundException("Personal event with id " + id + " doesn't exist"));
    }

    @Override
    public PersonalEvent update(PersonalEvent personalEvent, Long id) {
        return personalEventRepository.findById(id)
                .map(
                        pe -> {
                            pe.setEventDate(personalEvent.getEventDate());
                            pe.setUser(personalEvent.getUser());
                            pe.setEventDuration(personalEvent.getEventDuration());
                            pe.setEventType(personalEvent.getEventType());
                            pe.setEventStartTime(personalEvent.getEventStartTime());
                            pe.setEventTitle(personalEvent.getEventTitle());
                            return personalEventRepository.save(pe);
                        }
                )
                .orElseThrow(() -> new PersonalEventNotFoundException("Personal event with id " + id + " doesn't exist"));
    }

    @Override
    public List<PersonalEvent> getAll() {
        return personalEventRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!personalEventRepository.existsById(id)) {
            throw new PersonalEventNotFoundException("Personal event with id " + id + " doesn't exist");
        }
        personalEventRepository.deleteById(id);
    }
}
