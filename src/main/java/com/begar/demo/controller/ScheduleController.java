package com.begar.demo.controller;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Schedule;
import com.begar.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/schedules")
    public List<Schedule> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/schedules/{id}")
    public Schedule getSchedule(@PathVariable int id) {
        return scheduleService.getSchedule(id);
    }

    @PostMapping("/schedules")
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleService.addSchedule(schedule);
    }

    @PutMapping("/schedules")
    public String updateSchedule(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule);
    }

    @PatchMapping("/schedules/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Schedule schedule = scheduleService.getSchedule(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Schedule.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, schedule, v);
        });
        scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
    }

    @RequestMapping("/schedules-per-groups")
    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        return scheduleService.getSchedulesPerGroups();
    }
}
