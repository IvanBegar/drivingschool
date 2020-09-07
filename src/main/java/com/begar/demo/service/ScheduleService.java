package com.begar.demo.service;

import com.begar.demo.entity.Schedule;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getSchedule(int id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new DataException("Schedule with id: " + id +" don't exist!"));
    }

    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void updateSchedule(Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(schedule.getSchedule_id()).orElseThrow(
                () -> new DataException("Schedule with id: " + schedule.getSchedule_id() +" don't exist!"));
        existingSchedule.setName(schedule.getName());
        existingSchedule.setDescription(schedule.getDescription());
        scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
    }
}
