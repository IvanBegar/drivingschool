package com.begar.demo.repository;

import com.begar.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select sum(t.salary) from Teacher t")
    Double sumOfTeachersSalaries();
}
