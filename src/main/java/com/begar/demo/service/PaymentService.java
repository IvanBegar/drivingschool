package com.begar.demo.service;

import com.begar.demo.dto.response.IncomeForPeriodDTO;
import com.begar.demo.dto.response.PaymentForPeriodDTO;
import com.begar.demo.entity.Payment;
import com.begar.demo.entity.Student;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private VehiclePaymentRepository vehiclePaymentRepository;
    private UtilityPaymentRepository utilityPaymentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          StudentRepository studentRepository,
                          VehiclePaymentRepository vehiclePaymentRepository,
                          UtilityPaymentRepository utilityPaymentRepository,
                          TeacherRepository teacherRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.vehiclePaymentRepository = vehiclePaymentRepository;
        this.utilityPaymentRepository = utilityPaymentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(int id) {
        return paymentRepository.findById(id).orElseThrow(() -> new DataException("Payment with id: " + id +" don't exist!"));
    }

    public void addPayment(Payment payment, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new DataException("Student with id: " + id +" don't exist!"));
        payment.setStudent(student);
        paymentRepository.save(payment);
    }

    public void updatePayment(Payment payment) {
        Payment existingPayment = paymentRepository.findById(payment.getPayment_id()).orElseThrow(
                () -> new DataException("Payment with id: " + payment.getPayment_id() +" don't exist!"));
        existingPayment.setStudent(payment.getStudent());
        existingPayment.setComment(payment.getComment());
        existingPayment.setDate(payment.getDate());
        existingPayment.setSize(payment.getSize());
        paymentRepository.save(existingPayment);
    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }

    public IncomeForPeriodDTO getIncomeForPeriod(String str, String end) {
        return new IncomeForPeriodDTO(paymentRepository.getIncomeForPeriod(str, end));
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        double payments = vehiclePaymentRepository.vehiclePaymentsForPeriod(str, end) +
                utilityPaymentRepository.utilityPaymentsForPeriod(str, end) +
                teacherRepository.sumOfTeachersSalaries();
        return new PaymentForPeriodDTO(payments);
    }
}
