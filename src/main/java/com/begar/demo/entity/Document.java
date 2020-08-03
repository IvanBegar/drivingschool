package com.begar.demo.entity;

public class Document {

    private int idDocument;
    private int idStudent;
    private String photo;
    private String mainDocumentsCopies;
    private String medicalCertificate;

    @Override
    public String toString() {
        return "Document{" +
                "idDocument=" + idDocument +
                ", idStudent=" + idStudent +
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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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
