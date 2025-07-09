package org.example.diplomabackend.service;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.entity.Group;
import org.example.diplomabackend.exceptions.notFound.GroupNotFoundException;
import org.example.diplomabackend.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements IService<Group, Long> {

    private final GroupRepository groupRepository;

    @Override
    public Group addNew(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with id: " + id + " doesn't exist"));
    }

    @Override
    public Group update(Group group, Long id) {
        return groupRepository.findById(id)
                .map(
                        gr -> {
                            gr.setGroupNumber(group.getGroupNumber());
                            gr.setUniversity(group.getUniversity());
                            return groupRepository.save(gr);
                        }
                )
                .orElseThrow(() -> new GroupNotFoundException("Group with id: " + id + " doesn't exist"));
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException("Group with id: " + id + " doesn't exist");
        }
        groupRepository.deleteById(id);
    }
}
