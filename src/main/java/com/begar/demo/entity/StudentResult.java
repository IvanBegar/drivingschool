package com.begar.demo.entity;

public class StudentResult {

    private int result_id;
    private Student student;
    private String dateOfExam;
    private String resultInCenter;
    private String resultInSchool;

    @Override
    public String toString() {
        return "StudentResult{" +
                "idResult=" + result_id +
                ", idStudent=" + student +
                ", dateOfExam='" + dateOfExam + '\'' +
                ", resultInCenter='" + resultInCenter + '\'' +
                ", resultInSchool='" + resultInSchool + '\'' +
                '}';
    }

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
}
