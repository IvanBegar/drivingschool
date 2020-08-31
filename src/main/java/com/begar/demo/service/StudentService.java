package com.begar.demo.service;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import com.begar.demo.exception.DataException;
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

    public Student getStudentByPhoneNumber(String phone) {
        return studentRepository.getStudentByPhoneNumber(phone);
    }

    public void addStudent(Student student, int id) {
        studentRepository.addStudent(student, id);
    }

    public void updateStudent(Student student, int id) {
        if (studentRepository.getStudent(student.getStudent_id()).getStudent_id() == 0) {
            throw new DataException("Student don`t exist!");
        } else {
            studentRepository.updateStudent(student, id);
        }
    }

    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    public List<StudentPerCategoryDTO> getStudentsPerCategory(String cat) {
        return studentRepository.getStudentsPerCategory(cat);
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        return studentRepository.getStudentDocuments(id);
    }

    public List<Student> getStudentsPerGroup(int id) {
        return studentRepository.getStudentsPerGroup(id);
    }
}
