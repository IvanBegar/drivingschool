package com.begar.demo.service;

import com.begar.demo.entity.Teacher;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(int id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NoDataException("Teacher with id: " + id + " don't exist!"));
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(teacher.getTeacher_id()).orElseThrow(
                () -> new NoDataException("Teacher with id: " + teacher.getTeacher_id() + " don't exist!"));
        existingTeacher.setAddress(teacher.getAddress());
        existingTeacher.setPhone(teacher.getPhone());
        existingTeacher.setDateOfBirth(teacher.getDateOfBirth());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setMiddleName(teacher.getMiddleName());
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }
}


