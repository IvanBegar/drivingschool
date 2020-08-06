package com.begar.demo.entity;

public class Document {

    private int document_id;
    private Student student;
    private String photo;
    private String mainDocumentsCopies;
    private String medicalCertificate;

    @Override
    public String toString() {
        return "Document{" +
                "idDocument=" + document_id +
                ", idStudent=" + student +
                ", photo='" + photo + '\'' +
                ", mainDocumentsCopies='" + mainDocumentsCopies + '\'' +
                ", medicalCertificate='" + medicalCertificate + '\'' +
                '}';
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMainDocumentsCopies() {
        return mainDocumentsCopies;
    }

    public void setMainDocumentsCopies(String mainDocumentsCopies) {
        this.mainDocumentsCopies = mainDocumentsCopies;
    }

    public String getMedicalCertificate() {
        return medicalCertificate;
    }

    public void setMedicalCertificate(String medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
    }
}
