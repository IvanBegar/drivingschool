package com.begar.demo.service;


import com.begar.demo.entity.Group;
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
        return groupRepository.getGroup(id);
    }

    public void addGroup(Group group) {
        groupRepository.addGroup(group);
    }

    public String updateGroup(Group group) {
        String result = "";
        if (groupRepository.getGroup(group.getIdGroup()).getIdGroup() == 0) {
            result = "Group don`t exist!";
        } else {
            groupRepository.updateGroup(group);
            result = "Group successfully updated";
        }
        return result;
    }

    public void deleteGroup(int id) {
        groupRepository.deleteGroup(id);
    }
}