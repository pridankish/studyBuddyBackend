package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Subject;
import org.example.diplomabackend.exceptions.notFound.SubjectNotFoundException;
import org.example.diplomabackend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements IService<Subject, Long> {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject addNew(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " doesn't exist"));
    }

    @Override
    public Subject update(Subject subject, Long id) {
        return subjectRepository.findById(id)
                .map(
                        sub -> {
                            sub.setName(subject.getName());
                            sub.setLessons(subject.getLessons());
                            sub.setTasks(subject.getTasks());
                            return subjectRepository.save(sub);
                        }
                ).orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " doesn't exist"));
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException("Subject with id " + id + " doesn't exist");
        }
        subjectRepository.deleteById(id);
    }
}
