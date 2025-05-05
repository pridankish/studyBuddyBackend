package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.University;
import org.example.diplomabackend.exceptions.notFound.UniversityNotFoundException;
import org.example.diplomabackend.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService implements IService<University, Long> {

    private final UniversityRepository universityRepository;

    @Override
    public University addNew(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University getById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException("University with id " + id + " doesn't exist"));
    }

    @Override
    public University update(University university, Long id) {
        return universityRepository.findById(id)
                .map(
                        uni -> {
                            uni.setUniversityName(university.getUniversityName());
                            uni.setGroups(university.getGroups());
                            return universityRepository.save(uni);
                        }
                ).orElseThrow(() -> new UniversityNotFoundException("University with id " + id + " doesn't exist"));
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!universityRepository.existsById(id)) {
            throw new UniversityNotFoundException("University with id " + id + " doesn't exist");
        }
        universityRepository.deleteById(id);
    }
}
