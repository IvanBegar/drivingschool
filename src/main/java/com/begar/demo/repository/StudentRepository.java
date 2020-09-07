package com.begar.demo.repository;

import com.begar.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByPhone(String phone);

    @Query("select s from Student s inner join s.group g where g.group_id = :id")
    List<Student> getStudentsPerGroup(@Param("id") int id);
}
