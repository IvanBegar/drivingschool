package com.begar.demo.service;

import com.begar.demo.entity.Teacher;
import com.begar.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getTeachers() {
        return teacherRepository.getTeachers();
    }

    public Teacher getTeacher(int id) {
        return teacherRepository.getTeacher(id);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);
    }

    public String updateTeacher(Teacher teacher) {
        String result = "";
        if (teacherRepository.getTeacher(teacher.getTeacher_id()).getTeacher_id() == 0) {
            result = "Teacher don`t exist!";
        } else {
            teacherRepository.updateTeacher(teacher);
            result = "Teacher successfully updated";
        }
        return result;
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteTeacher(id);
    }

    public void addTeacherToGroup(int id1, int id2) {
        teacherRepository.addTeacherToGroup(id1, id2);
    }
}


