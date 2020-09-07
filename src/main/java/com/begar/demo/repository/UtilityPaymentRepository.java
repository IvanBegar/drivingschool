package com.begar.demo.repository;

import com.begar.demo.entity.UtilityPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityPaymentRepository extends JpaRepository<UtilityPayment, Integer> {

    @Query("select sum(u.size) from UtilityPayment u where u.date between :startDate and :endDate")
    Double utilityPaymentsForPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
