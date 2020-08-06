package com.begar.demo.service;

import com.begar.demo.entity.StudentResult;
import com.begar.demo.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentResultService {

    @Autowired
    private StudentResultRepository studentResultRepository;

    public List<StudentResult> getStudentResults() {
        return studentResultRepository.getStudentResults();
    }

    public StudentResult getStudentResult(int id) {
        return studentResultRepository.getStudentResult(id);
    }

    public void addStudentResult(StudentResult studentResult, int id) {
        studentResultRepository.addStudentResult(studentResult, id);
    }

    public String updateStudentResult(StudentResult studentResult, int id) {
        String result = "";
        if (studentResultRepository.getStudentResult(studentResult.getResult_id()).getResult_id() == 0) {
            result = "StudentResult don`t exist!";
        } else {
            studentResultRepository.updateStudentResult(studentResult, id);
            result = "StudentResult successfully updated";
        }
        return result;
    }

    public void deleteStudentResult(int id) {
        studentResultRepository.deleteStudentResult(id);
    }
}

