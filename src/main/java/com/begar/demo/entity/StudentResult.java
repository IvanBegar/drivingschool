package com.begar.demo.entity;

public class StudentResult {

    private int idResult;
    private int idStudent;
    private String dateOfExam;
    private String resultInCenter;
    private String resultInSchool;

    @Override
    public String toString() {
        return "StudentResult{" +
                "idResult=" + idResult +
                ", idStudent=" + idStudent +
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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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
