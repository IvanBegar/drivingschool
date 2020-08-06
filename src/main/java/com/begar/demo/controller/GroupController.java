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

    @PostMapping("/groups/id-category={id1}+id-schedule={id2}")
    public void addGroup(@RequestBody Group group, @PathVariable int id1, @PathVariable int id2) {
        groupService.addGroup(group, id1, id2);
    }

    @PutMapping("/groups/id-category={id1}+id-schedule={id2}")
    public String updateGroup(@RequestBody Group group, @PathVariable int id1, @PathVariable int id2) {
        return groupService.updateGroup(group, id1, id2);
    }

    @PatchMapping("/groups/id-group={id1}+id-category={id2}+id-schedule={id3}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @PathVariable int id3, @RequestBody Map<Object, Object> fields) {
        Group group = groupService.getGroup(id1);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Group.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, group, v);
        });
        groupService.updateGroup(group, id2, id3);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }
}

