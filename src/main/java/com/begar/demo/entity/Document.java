package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "document", schema = "hibernate_db")
public class Document {

    @Id
    @Column(name = "document_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int document_id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column(name = "photo")
    private String photo;
    @Column(name = "mainDocumentsCopies")
    private String mainDocumentsCopies;
    @Column(name = "medicalCertificate")
    private String medicalCertificate;

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

    @Override
    public String toString() {
        return "Document{" +
                "document_id=" + document_id +
                ", student=" + student +
                ", photo='" + photo + '\'' +
                ", mainDocumentsCopies='" + mainDocumentsCopies + '\'' +
                ", medicalCertificate='" + medicalCertificate + '\'' +
                '}';
    }
}
