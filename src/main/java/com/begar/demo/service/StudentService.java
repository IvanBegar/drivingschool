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

    public void addStudent(Student s1) {
        studentRepository.addStudent(s1);
    }

    public String updateStudent(Student s1) {
        String result = "";
        if (studentRepository.getStudent(s1.getIdStudent()).getIdStudent() == 0) {
            result = "Student don`t exist!";
        } else {
            studentRepository.updateStudent(s1);
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
