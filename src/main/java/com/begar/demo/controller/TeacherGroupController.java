package com.begar.demo.controller;

import com.begar.demo.entity.TeacherGroup;
import com.begar.demo.service.TeacherGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherGroupController {

    @Autowired
    private TeacherGroupService teacherGroupService;

    @RequestMapping("/teacher-groups")
    public List<TeacherGroup> getTeacherGroups() {
        return teacherGroupService.getTeacherGroups();
    }

    @GetMapping("/teacher-groups/{id}")
    public TeacherGroup getTeacherGroup(@PathVariable int id) {
        return teacherGroupService.getTeacherGroup(id);
    }

    @PostMapping("/teacher-groups")
    public void addTeacherGroup(@RequestBody TeacherGroup teacherGroup) {
        teacherGroupService.addTeacherGroup(teacherGroup);
    }

    @PutMapping("/teacher-groups")
    public String updateTeacherGroup(@RequestBody TeacherGroup teacherGroup) {
        return teacherGroupService.updateTeacherGroup(teacherGroup);
    }

    @PatchMapping("/teacher-groups/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        TeacherGroup teacherGroup = teacherGroupService.getTeacherGroup(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(TeacherGroup.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, teacherGroup, v);
        });
        teacherGroupService.updateTeacherGroup(teacherGroup);
    }

    @DeleteMapping("/teacher-groups/{id}")
    public void deleteTeacherGroup(@PathVariable int id) {
        teacherGroupService.deleteTeacherGroup(id);
    }
}


