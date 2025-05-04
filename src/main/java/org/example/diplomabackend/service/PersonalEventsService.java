package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.PersonalEvent;
import org.example.diplomabackend.exceptions.notFound.PersonalEventNotFoundException;
import org.example.diplomabackend.repository.LessonRepository;
import org.example.diplomabackend.repository.PersonalEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalEventsService implements IService<PersonalEvent, Long> {

    private final PersonalEventRepository personalEventRepository;
    private final LessonRepository lessonRepository;

    @Override
    public PersonalEvent addNew(PersonalEvent personalEvent) {

        // TODO: добавить поиск по пользователю, в сессии которого находимся

        var lessonsThisDay= lessonRepository.getLessonsByLessonDate(personalEvent.getEventDate()).stream()
                .filter(l -> personalEvent.getEventStartTime().isAfter(l.getStartTime()) && personalEvent.getEventStartTime().isBefore(l.getEndTime()))
                .toList();

        var personalEventsThisDay= lessonRepository.getLessonsByLessonDate(personalEvent.getEventDate()).stream()
                .filter(pe -> personalEvent.getEventStartTime().isAfter(pe.getStartTime()) && personalEvent.getEventStartTime().isBefore(pe.getEndTime()))
                .toList();

        if (lessonsThisDay.isEmpty() && personalEventsThisDay.isEmpty()) {
            return personalEventRepository.save(personalEvent);
        } else {
            // TODO: new exception class for that case
            throw new PersonalEventNotFoundException("You cannot create new personal event. This time is booked by lesson or another personal event.");
        }
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
