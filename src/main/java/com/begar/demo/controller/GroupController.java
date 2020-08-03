package com.begar.demo.controller;

import com.begar.demo.entity.Group;
import com.begar.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping("/groups")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/groups/{id}")
    public Group getTeacher(@PathVariable int id) {
        return groupService.getGroup(id);
    }

    @PostMapping("/groups")
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
    }

    @PutMapping("/groups")
    public String updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @PatchMapping("/groups/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Group group = groupService.getGroup(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Group.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, group, v);
        });
        groupService.updateGroup(group);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }
}

