package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.LessonType;
import org.example.diplomabackend.repository.LessonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonTypeService implements IService<LessonType, Long> {

    private final LessonTypeRepository lessonTypeRepository;

    @Override
    public LessonType addNew(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    @Override
    public LessonType getById(Long id) {
        return lessonTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LessonType with id " + id + " not found"));
    }

    @Override
    public LessonType update(LessonType lessonType, Long id) {
        return lessonTypeRepository.findById(id).map(
                lt -> {
                    lt.setName(lessonType.getName());
                    return lessonTypeRepository.save(lt);
                }
        ).orElseThrow(() -> new RuntimeException("LessonType with id " + id + " not found"));
    }

    @Override
    public List<LessonType> getAll() {
        return lessonTypeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!lessonTypeRepository.existsById(id)) {
            throw new RuntimeException("LessonType with id " + id + " not found");
        }
        lessonTypeRepository.deleteById(id);
    }
}
