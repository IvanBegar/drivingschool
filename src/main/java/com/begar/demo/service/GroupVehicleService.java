package com.begar.demo.service;

import com.begar.demo.entity.GroupVehicle;
import com.begar.demo.repository.GroupVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupVehicleService {

    @Autowired
    private GroupVehicleRepository groupVehicleRepository;

    public List<GroupVehicle> getGroupVehicles() {
        return groupVehicleRepository.getGroupVehicles();
    }

    public GroupVehicle getGroupVehicle(int id) {
        return groupVehicleRepository.getGroupVehicle(id);
    }

    public void addGroupVehicle(GroupVehicle groupVehicle) {
        groupVehicleRepository.addGroupVehicle(groupVehicle);
    }

    public String updateGroupVehicle(GroupVehicle groupVehicle) {
        String result = "";
        if (groupVehicleRepository.getGroupVehicle(groupVehicle.getIdGroupVehicle()).getIdGroupVehicle() == 0) {
            result = "GroupVehicle don`t exist!";
        } else {
            groupVehicleRepository.updateGroupVehicle(groupVehicle);
            result = "GroupVehicle successfully updated";
        }
        return result;
    }

    public void deleteGroupVehicle(int id) {
        groupVehicleRepository.deleteGroupVehicle(id);
    }
}
