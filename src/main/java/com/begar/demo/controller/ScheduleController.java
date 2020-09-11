package com.begar.demo.controller;

import com.begar.demo.entity.Schedule;
import com.begar.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Schedule> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable int id) {
        return scheduleService.getSchedule(id);
    }

    @PostMapping
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleService.addSchedule(schedule);
    }

    @PutMapping
    public void updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Schedule schedule = scheduleService.getSchedule(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Schedule.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, schedule, v);
        });
        scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
    }
}
