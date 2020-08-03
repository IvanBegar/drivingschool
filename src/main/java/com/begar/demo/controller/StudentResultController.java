package com.begar.demo.controller;

import com.begar.demo.entity.StudentResult;
import com.begar.demo.service.StudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class StudentResultController {

    @Autowired
    private StudentResultService studentResultService;

    @RequestMapping("/student-results")
    public List<StudentResult> getStudentResults() {
        return studentResultService.getStudentResults();
    }

    @GetMapping("/student-results/{id}")
    public StudentResult getStudentResult(@PathVariable int id) {
        return studentResultService.getStudentResult(id);
    }

    @PostMapping("/student-results")
    public void addStudentResult(@RequestBody StudentResult studentResult) {
        studentResultService.addStudentResult(studentResult);
    }

    @PutMapping("/student-results")
    public String updateStudentResult(@RequestBody StudentResult studentResult) {
        return studentResultService.updateStudentResult(studentResult);
    }

    @PatchMapping("/student-results/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        StudentResult studentResult = studentResultService.getStudentResult(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(StudentResult.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, studentResult, v);
        });
        studentResultService.updateStudentResult(studentResult);
    }

    @DeleteMapping("/student-results/{id}")
    public void deleteStudentResult(@PathVariable int id) {
        studentResultService.deleteStudentResult(id);
    }
}

