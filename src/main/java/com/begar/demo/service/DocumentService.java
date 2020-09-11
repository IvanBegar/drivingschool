package com.begar.demo.service;

import com.begar.demo.dto.response.StudentDocumentsDTO;
import com.begar.demo.entity.Document;
import com.begar.demo.entity.Student;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.DocumentRepository;
import com.begar.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocument(int id) {
        return documentRepository.findById(id).orElseThrow(() -> new NoDataException("Document with id: " + id + " don't exist!"));
    }

    public void addDocument(Document document, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoDataException("Student with id: " + id + " don't exist!"));
        document.setStudent(student);
        documentRepository.save(document);
    }

    public void updateDocument(Document document) {
        Document existingDocument = documentRepository.findById(document.getDocument_id()).orElseThrow(
                () -> new NoDataException("Document with id: " + document.getDocument_id() + " don't exist!"));
        existingDocument.setStudent(document.getStudent());
        existingDocument.setPhoto(document.getPhoto());
        existingDocument.setMedicalCertificate(document.getMedicalCertificate());
        existingDocument.setMainDocumentsCopies(document.getMainDocumentsCopies());
        documentRepository.save(existingDocument);
    }

    public void deleteDocument(int id) {
        documentRepository.deleteById(id);
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        return documentRepository.getStudentDocuments(id);
    }
}
