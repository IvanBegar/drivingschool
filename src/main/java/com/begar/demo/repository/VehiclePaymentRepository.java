package com.begar.demo.repository;

import com.begar.demo.entity.VehiclePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePaymentRepository extends JpaRepository<VehiclePayment, Integer> {

    @Query("select sum(v.size) from VehiclePayment v where v.date between :startDate and :endDate")
    Double vehiclePaymentsForPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
