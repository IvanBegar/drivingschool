package com.begar.demo.controller;

import com.begar.demo.entity.GroupVehicle;
import com.begar.demo.service.GroupVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class GroupVehicleController {

    @Autowired
    private GroupVehicleService groupVehicleService;

    @RequestMapping("/group-vehicle")
    public List<GroupVehicle> getGroupVehicles() {
        return groupVehicleService.getGroupVehicles();
    }

    @GetMapping("/group-vehicle/{id}")
    public GroupVehicle getGroupVehicle(@PathVariable int id) {
        return groupVehicleService.getGroupVehicle(id);
    }

    @PostMapping("/group-vehicle")
    public void addGroupVehicle(@RequestBody GroupVehicle groupVehicle) {
        groupVehicleService.addGroupVehicle(groupVehicle);
    }

    @PutMapping("/group-vehicle")
    public String updateGroupVehicle(@RequestBody GroupVehicle groupVehicle) {
        return groupVehicleService.updateGroupVehicle(groupVehicle);
    }

    @PatchMapping("/group-vehicle/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        GroupVehicle groupVehicle = groupVehicleService.getGroupVehicle(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(GroupVehicle.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, groupVehicle, v);
        });
        groupVehicleService.updateGroupVehicle(groupVehicle);
    }

    @DeleteMapping("/group-vehicle/{id}")
    public void deleteGroupVehicle(@PathVariable int id) {
        groupVehicleService.deleteGroupVehicle(id);
    }
}
