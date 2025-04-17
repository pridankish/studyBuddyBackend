package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Schedule;
import org.example.diplomabackend.exceptions.notFound.ScheduleNotFoundException;
import org.example.diplomabackend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IService<Schedule, Long> {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule addNew(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with id " + id + " doesn't exist"));
    }

    @Override
    public Schedule update(Schedule schedule, Long id) {
        return scheduleRepository.findById(id)
                .map(
                        sch -> {
                            sch.setGroup(schedule.getGroup());
                            return scheduleRepository.save(sch);
                        }
                )
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with id " + id + " doesn't exist"));
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException("Schedule with id " + id + " doesn't exist");
        }
        scheduleRepository.deleteById(id);
    }
}
