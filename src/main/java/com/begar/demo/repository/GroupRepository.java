package com.begar.demo.repository;

import com.begar.demo.dto.response.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("select new com.begar.demo.dto.response.SchedulesPerGroupsDTO(g.groupName, s.name, s.description) from Group g inner join g.schedule s")
    List<SchedulesPerGroupsDTO> getSchedulesPerGroups();
}
