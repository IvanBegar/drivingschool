package com.begar.demo.service;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import com.begar.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public Student getStudent(int id) {
        return studentRepository.getStudent(id);
    }

    public void addStudent(Student student, int id) {
        studentRepository.addStudent(student, id);
    }

    public String updateStudent(Student student, int id) {
        String result = "";
        if (studentRepository.getStudent(student.getStudent_id()).getStudent_id() == 0) {
            result = "Student don`t exist!";
        } else {
            studentRepository.updateStudent(student, id);
            result = "Student successfully updated";
        }
        return result;
    }

    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    public StudentPerCategoryDTO getStudentsPerCategory(String cat) {
        return studentRepository.getStudentsPerCategory(cat);
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        return studentRepository.getStudentDocuments(id);
    }

    public List<Student> getStudentsPerGroup(int id) {
        return studentRepository.getStudentsPerGroup(id);
    }
}
