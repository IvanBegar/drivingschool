package com.begar.demo.controller;

import com.begar.demo.entity.Teacher;
import com.begar.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/teachers/{id}")
    public Teacher getTeacher(@PathVariable int id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping("/teachers")
    public void addTeacher(@RequestBody Teacher t1) {
        teacherService.addTeacher(t1);
    }

    @PutMapping("/teachers")
    public String updateStudent(@RequestBody Teacher t1) {
        return teacherService.updateTeacher(t1);
    }

    @PatchMapping("/teachers/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Teacher teacher = teacherService.getTeacher(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Teacher.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, teacher, v);
        });
        teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
    }
}
