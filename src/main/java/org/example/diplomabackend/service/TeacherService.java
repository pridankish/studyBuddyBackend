package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Teacher;
import org.example.diplomabackend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements IService<Teacher, Long> {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher addNew(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher with id " + id + " not found"));
    }

    @Override
    public Teacher update(Teacher teacher, Long id) {
        return teacherRepository.findById(id).map(
                t -> {
                    t.setFirstName(teacher.getFirstName());
                    t.setMiddleName(teacher.getMiddleName());
                    t.setLastName(teacher.getLastName());
                    return teacherRepository.save(t);
                }
        ).orElseThrow(() -> new RuntimeException("Teacher with id " + id + " not found"));
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher with id " + id + " not found");
        }
        teacherRepository.deleteById(id);
    }
}
