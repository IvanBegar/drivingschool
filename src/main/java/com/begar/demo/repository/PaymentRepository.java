package com.begar.demo.repository;

import com.begar.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("select sum(p.size) from Payment as p where p.date between :startDate and :endDate")
    Double getIncomeForPeriod(@Param("startDate") String str, @Param("endDate") String end);
}
