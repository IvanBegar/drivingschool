package com.begar.demo.service;

import com.begar.demo.dto.response.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Category;
import com.begar.demo.entity.Group;
import com.begar.demo.entity.Schedule;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.CategoryRepository;
import com.begar.demo.repository.GroupRepository;
import com.begar.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {


    private GroupRepository groupRepository;
    private ScheduleRepository scheduleRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository,
                        ScheduleRepository scheduleRepository,
                        CategoryRepository categoryRepository) {
        this.groupRepository = groupRepository;
        this.scheduleRepository = scheduleRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public Group getGroup(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new DataException("Group with id: " + id +" don't exist!"));
    }

    public void addGroup(Group group, String category_name, String schedule_name) {
        Schedule schedule = scheduleRepository.findByName(schedule_name);
        Category category = categoryRepository.findByName(category_name);
        group.setSchedule(schedule);
        group.setCategory(category);
        groupRepository.save(group);
    }

    public void updateGroup(Group group) {
        Group existingGroup = groupRepository.findById(group.getGroup_id()).orElseThrow(
                () -> new DataException("Group with id: " + group.getGroup_id() +" don't exist!"));
        existingGroup.setCategory(group.getCategory());
        existingGroup.setSchedule(group.getSchedule());
        existingGroup.setGroupName(group.getGroupName());
        existingGroup.setStartDate(group.getStartDate());
        existingGroup.setEndDate(group.getEndDate());
        existingGroup.setVehicles(group.getVehicles());
        existingGroup.setTeachers(group.getTeachers());
        groupRepository.save(existingGroup);
    }

    public void deleteGroup(int id) {
        groupRepository.deleteById(id);
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        return groupRepository.getSchedulesPerGroups();
    }
}
