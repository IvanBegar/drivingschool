package com.begar.demo.controller;

import com.begar.demo.dto.response.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Group;
import com.begar.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/category-name={cat}+schedule-name={sch}")
    public void addGroup(@RequestBody Group group, @PathVariable String cat, @PathVariable String sch) {
        groupService.addGroup(group, cat, sch);
    }

    @PutMapping
    public void updateGroup(@RequestBody Group group) {
        groupService.updateGroup(group);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Group group = groupService.getGroup(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Group.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, group, v);
        });
        groupService.updateGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        return groupService.getSchedulesPerGroups();
    }
}

