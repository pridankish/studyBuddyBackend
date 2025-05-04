package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.User;
import org.example.diplomabackend.exceptions.notFound.UserNotFoundException;
import org.example.diplomabackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, Long>, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    // РЕГИСТРАЦИЯ

    public boolean register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " doesn't exist"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
