package com.begar.demo.controller;

import com.begar.demo.entity.Group;
import com.begar.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public Group getTeacher(@PathVariable int id) {
        return groupService.getGroup(id);
    }

    @PostMapping("/category-id={id1}+schedule-id={id2}")
    public void addGroup(@RequestBody Group group, @PathVariable int id1, @PathVariable int id2) {
        groupService.addGroup(group, id1, id2);
    }

    @PutMapping("/category-id={id1}+schedule-id={id2}")
    public void updateGroup(@RequestBody Group group, @PathVariable int id1, @PathVariable int id2) {
        groupService.updateGroup(group, id1, id2);
    }

    @PatchMapping("/group-id={id1}+category-id={id2}+schedule-id={id3}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @PathVariable int id3, @RequestBody Map<Object, Object> fields) {
        Group group = groupService.getGroup(id1);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Group.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, group, v);
        });
        groupService.updateGroup(group, id2, id3);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }
}

