package com.begar.demo.service;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
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
        return scheduleRepository.getSchedules();
    }

    public Schedule getSchedule(int id) {
        return scheduleRepository.getSchedule(id);
    }

    public void addSchedule(Schedule schedule) {
        scheduleRepository.addSchedule(schedule);
    }

    public void updateSchedule(Schedule schedule) {
        if (scheduleRepository.getSchedule(schedule.getSchedule_id()).getSchedule_id() == 0) {
            throw new DataException("Schedule don`t exist!");
        } else {
            scheduleRepository.updateSchedule(schedule);
        }
    }

    public void deleteSchedule(int id) {
        scheduleRepository.deleteSchedule(id);
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        return scheduleRepository.getSchedulesPerGroups();
    }
}
