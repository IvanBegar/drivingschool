package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_result", schema = "hibernate_db")
public class StudentResult {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int result_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Column(name = "dateOfExam")
    private String dateOfExam;

    @Column(name = "resultInCenter")
    private String resultInCenter;

    @Column(name = "resultInSchool")
    private String resultInSchool;

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(String dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public String getResultInCenter() {
        return resultInCenter;
    }

    public void setResultInCenter(String resultInCenter) {
        this.resultInCenter = resultInCenter;
    }

    public String getResultInSchool() {
        return resultInSchool;
    }

    public void setResultInSchool(String resultInSchool) {
        this.resultInSchool = resultInSchool;
    }

    @Override
    public String toString() {
        return "StudentResult{" +
                "result_id=" + result_id +
                ", student=" + student +
                ", dateOfExam='" + dateOfExam + '\'' +
                ", resultInCenter='" + resultInCenter + '\'' +
                ", resultInSchool='" + resultInSchool + '\'' +
                '}';
    }
}
