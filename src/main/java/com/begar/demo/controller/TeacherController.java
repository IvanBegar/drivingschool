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
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable int id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping
    public void addTeacher(@RequestBody Teacher t1) {
        teacherService.addTeacher(t1);
    }

    @PutMapping
    public void updateStudent(@RequestBody Teacher t1) {
        teacherService.updateTeacher(t1);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Teacher teacher = teacherService.getTeacher(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Teacher.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, teacher, v);
        });
        teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
    }
}
