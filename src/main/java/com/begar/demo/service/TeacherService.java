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

    public void addTeacher(Teacher t1) {
        teacherRepository.addTeacher(t1);
    }

    public String updateTeacher(Teacher t1) {
        String result = "";
        if (teacherRepository.getTeacher(t1.getIdTeacher()).getIdTeacher() == 0) {
            result = "Teacher don`t exist!";
        } else {
            teacherRepository.updateTeacher(t1);
            result = "Teacher successfully updated";
        }
        return result;
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteTeacher(id);
    }
}


