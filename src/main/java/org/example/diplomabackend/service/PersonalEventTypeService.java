package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.PersonalEventType;
import org.example.diplomabackend.repository.PersonalEventTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalEventTypeService implements IService<PersonalEventType, Long> {

    private final PersonalEventTypeRepository personalEventTypeRepository;

    @Override
    public PersonalEventType addNew(PersonalEventType personalEventType) {
        return personalEventTypeRepository.save(personalEventType);
    }

    @Override
    public PersonalEventType getById(Long id) {
        return personalEventTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal Event Type Not Found"));
    }

    @Override
    public PersonalEventType update(PersonalEventType personalEventType, Long id) {
        return personalEventTypeRepository.findById(id).map(
                perType -> {
                    perType.setName(personalEventType.getName());
                    perType.setPersonalEvents(personalEventType.getPersonalEvents());
                    return personalEventTypeRepository.save(perType);
                }
        ).orElseThrow(() -> new RuntimeException("Personal Event Type Not Found"));
    }

    @Override
    public List<PersonalEventType> getAll() {
        return personalEventTypeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!personalEventTypeRepository.existsById(id)) {
            throw new RuntimeException("Personal Event Type Not Found");
        }
        personalEventTypeRepository.deleteById(id);
    }
}
