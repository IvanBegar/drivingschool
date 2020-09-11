package com.begar.demo.controller;

import com.begar.demo.entity.Student;
import com.begar.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/id={id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/email={email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @PostMapping("/group-id={id}")
    public void addStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.addStudent(student, id);
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Student student = studentService.getStudent(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Student.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, student, v);
        });
        studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping("/per-group/{id}")
    public List<Student> getStudentsPerGroup(@PathVariable int id) {
        return studentService.getStudentsPerGroup(id);
    }
}
