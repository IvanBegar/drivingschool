package com.begar.demo.service;

import com.begar.demo.entity.Group;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getGroups() {
        return groupRepository.getGroups();
    }

    public Group getGroup(int id) {
        Group group = groupRepository.getGroup(id);
        if (group == null) {
            throw new DataException("No group with this id!");
        }
        return group;
    }

    public void addGroup(Group group, int category_id, int schedule_id) {
        groupRepository.addGroup(group, category_id, schedule_id);
    }

    public void updateGroup(Group group, int id1, int id2) {
        if (groupRepository.getGroup(group.getGroup_id()).getGroup_id() == 0) {
            throw new DataException("Group don`t exist!");
        } else {
            groupRepository.updateGroup(group, id1, id2);
        }
    }

    public void deleteGroup(int id) {
        groupRepository.deleteGroup(id);
    }
}
