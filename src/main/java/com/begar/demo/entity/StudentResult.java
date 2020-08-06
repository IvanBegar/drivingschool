package com.begar.demo.entity;

import com.begar.demo.dto.StudentDTO;

public class StudentResult {

    private int idResult;
    private StudentDTO student;
    private String dateOfExam;
    private String resultInCenter;
    private String resultInSchool;

    @Override
    public String toString() {
        return "StudentResult{" +
                "idResult=" + idResult +
                ", idStudent=" + student +
                ", dateOfExam='" + dateOfExam + '\'' +
                ", resultInCenter='" + resultInCenter + '\'' +
                ", resultInSchool='" + resultInSchool + '\'' +
                '}';
    }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
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
}
