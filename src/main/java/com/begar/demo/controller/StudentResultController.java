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

    @PostMapping("/student-results/student-id={id}")
    public void addStudentResult(@RequestBody StudentResult studentResult, @PathVariable int id) {
        studentResultService.addStudentResult(studentResult, id);
    }

    @PutMapping("/student-results/student-id={id}")
    public String updateStudentResult(@RequestBody StudentResult studentResult, @PathVariable int id) {
        return studentResultService.updateStudentResult(studentResult, id);
    }

    @PatchMapping("/student-results/result-id={id1}+student-id={id2}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @RequestBody Map<Object, Object> fields) {
        StudentResult studentResult = studentResultService.getStudentResult(id1);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(StudentResult.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, studentResult, v);
        });
        studentResultService.updateStudentResult(studentResult, id2);
    }

    @DeleteMapping("/student-results/{id}")
    public void deleteStudentResult(@PathVariable int id) {
        studentResultService.deleteStudentResult(id);
    }
}

