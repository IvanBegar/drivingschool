package com.begar.demo.service;

import com.begar.demo.entity.TeacherGroup;
import com.begar.demo.repository.TeacherGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherGroupService {

    @Autowired
    private TeacherGroupRepository teacherGroupRepository;

    public List<TeacherGroup> getTeacherGroups() {
        return teacherGroupRepository.getTeacherGroups();
    }

    public TeacherGroup getTeacherGroup(int id) {
        return teacherGroupRepository.getTeacherGroup(id);
    }

    public void addTeacherGroup(TeacherGroup teacherGroup) {
        teacherGroupRepository.addTeacherGroup(teacherGroup);
    }

    public String updateTeacherGroup(TeacherGroup teacherGroup) {
        String result = "";
        if (teacherGroupRepository.getTeacherGroup(teacherGroup.getIdTeacherGroup()).getIdTeacherGroup() == 0) {
            result = "TeacherGroup don`t exist!";
        } else {
            teacherGroupRepository.updateTeacherGroup(teacherGroup);
            result = "TeacherGroup successfully updated";
        }
        return result;
    }

    public void deleteTeacherGroup(int id) {
        teacherGroupRepository.deleteTeacherGroup(id);
    }
}
