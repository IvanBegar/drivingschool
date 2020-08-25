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
@RequestMapping("/student-results")
@CrossOrigin
public class StudentResultController {

    @Autowired
    private StudentResultService studentResultService;

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentResult> getStudentResults() {
        return studentResultService.getStudentResults();
    }

    @GetMapping("/{id}")
    public StudentResult getStudentResult(@PathVariable int id) {
        return studentResultService.getStudentResult(id);
    }

    @PostMapping("/student-id={id}")
    public void addStudentResult(@RequestBody StudentResult studentResult, @PathVariable int id) {
        studentResultService.addStudentResult(studentResult, id);
    }

    @PutMapping("/student-id={id}")
    public void updateStudentResult(@RequestBody StudentResult studentResult, @PathVariable int id) {
        studentResultService.updateStudentResult(studentResult, id);
    }

    @PatchMapping("/result-id={id1}+student-id={id2}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @RequestBody Map<Object, Object> fields) {
        StudentResult studentResult = studentResultService.getStudentResult(id1);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(StudentResult.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, studentResult, v);
        });
        studentResultService.updateStudentResult(studentResult, id2);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentResult(@PathVariable int id) {
        studentResultService.deleteStudentResult(id);
    }
}

