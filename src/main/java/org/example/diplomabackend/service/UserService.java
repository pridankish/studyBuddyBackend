package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.User;
import org.example.diplomabackend.exceptions.notFound.UserNotFoundException;
import org.example.diplomabackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, Long> {

    private final UserRepository userRepository;

    @Override
    public User addNew(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist"));
    }

    @Override
    public User update(User user, Long id) {
        return userRepository.findById(id)
                .map(
                        u -> {
                            u.setFirstName(user.getFirstName());
                            u.setLastName(user.getLastName());
                            u.setEmail(user.getEmail());
                            u.setPassword(user.getPassword());
                            u.setCreatedAt(user.getCreatedAt());
                            u.setGroup(user.getGroup());
                            u.setPersonalEvents(user.getPersonalEvents());
                            u.setUniversity(user.getUniversity());
                            return userRepository.save(u);
                        }
                ).orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " doesn't exist");
        }
        userRepository.deleteById(id);
    }
}
