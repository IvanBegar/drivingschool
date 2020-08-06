package com.begar.demo.entity;

import com.begar.demo.dto.StudentDTO;

public class Document {

    private int idDocument;
    private StudentDTO student;
    private String photo;
    private String mainDocumentsCopies;
    private String medicalCertificate;

    @Override
    public String toString() {
        return "Document{" +
                "idDocument=" + idDocument +
                ", idStudent=" + student +
                ", photo='" + photo + '\'' +
                ", mainDocumentsCopies='" + mainDocumentsCopies + '\'' +
                ", medicalCartificate='" + medicalCertificate + '\'' +
                '}';
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
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

    public void setMedicalCertificate(String medicalCartificate) {
        this.medicalCertificate = medicalCartificate;
    }
}
