package com.begar.demo.dto.response;

public class StudentDocumentsDTO {

    private String firstName;
    private String lastName;
    private String photo;
    private String mainDocumentsCopies;
    private String medicalCertificate;

    public StudentDocumentsDTO(String firstName, String lastName, String photo, String mainDocumentsCopies, String medicalCertificate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.mainDocumentsCopies = mainDocumentsCopies;
        this.medicalCertificate = medicalCertificate;
    }

    public StudentDocumentsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "StudentDocumentsDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo='" + photo + '\'' +
                ", mainDocumentsCopies='" + mainDocumentsCopies + '\'' +
                ", medicalCertificate='" + medicalCertificate + '\'' +
                '}';
    }
}
