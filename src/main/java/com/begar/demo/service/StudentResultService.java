package com.begar.demo.service;

import com.begar.demo.entity.Student;
import com.begar.demo.entity.StudentResult;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.StudentRepository;
import com.begar.demo.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResultService {

    @Autowired
    private StudentResultRepository studentResultRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentResult> getStudentResults() {
        return studentResultRepository.findAll();
    }

    public StudentResult getStudentResult(int id) {
        return studentResultRepository.findById(id).orElseThrow(() -> new NoDataException("Student Result with id: " + id + " don't exist!"));
    }

    public void addStudentResult(StudentResult studentResult, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoDataException("Student with id: " + id + " don't exist!"));
        studentResult.setStudent(student);
        studentResultRepository.save(studentResult);
    }

    public void updateStudentResult(StudentResult studentResult) {
        StudentResult existingStudentResult = studentResultRepository.findById(studentResult.getResult_id()).orElseThrow(
                () -> new NoDataException("Student Result with id: " + studentResult.getResult_id() + " don't exist!"));
        existingStudentResult.setStudent(studentResult.getStudent());
        existingStudentResult.setDateOfExam(studentResult.getDateOfExam());
        existingStudentResult.setResultInCenter(studentResult.getResultInCenter());
        existingStudentResult.setResultInSchool(studentResult.getResultInSchool());
        studentResultRepository.save(existingStudentResult);
    }

    public void deleteStudentResult(int id) {
        studentResultRepository.deleteById(id);
    }
}

