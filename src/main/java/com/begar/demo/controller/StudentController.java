package com.begar.demo.controller;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import com.begar.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/group-id={id}")
    public void addStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.addStudent(student, id);
    }

    @PutMapping("/group-id={id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.updateStudent(student, id);
    }

    @PatchMapping("/student-id={id1}+group-id={id2}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @RequestBody Map<Object, Object> fields) {
        Student student = studentService.getStudent(id1);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Student.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, student, v);
        });
        studentService.updateStudent(student, id2);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping("/students-per-category/{cat}")
    public StudentPerCategoryDTO getStudentsPerCategory(@PathVariable String cat) {
        return studentService.getStudentsPerCategory(cat);
    }

    @RequestMapping("/student-documents/{id}")
    public StudentDocumentsDTO getStudentDocuments(@PathVariable int id) {
        return studentService.getStudentDocuments(id);
    }

    @RequestMapping("/students-per-group/{id}")
    public List<Student> getStudentsPerGroup(@PathVariable int id) {
        return studentService.getStudentsPerGroup(id);
    }
}
