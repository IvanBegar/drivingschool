package com.begar.demo.service;

import com.begar.demo.entity.Group;
import com.begar.demo.entity.Student;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.GroupRepository;
import com.begar.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElseThrow(() -> new DataException("Student with id: " + id +" don't exist!"));
    }

    public Student getStudentByPhoneNumber(String phone) {
        return studentRepository.findByPhone(phone);
    }

    public void addStudent(Student student, int id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new DataException("Group with id: " + id +" don't exist!"));
        student.setGroup(group);
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        Student existingStudent = studentRepository.findById(student.getStudent_id()).orElseThrow(
                () -> new DataException("Student with id: " + student.getStudent_id() +" don't exist!"));
        existingStudent.setAddress(student.getAddress());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setMiddleName(student.getMiddleName());
        existingStudent.setFirstName(student.getFirstName());
        studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsPerGroup(int id) {
        return studentRepository.getStudentsPerGroup(id);
    }

}
