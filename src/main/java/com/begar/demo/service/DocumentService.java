package com.begar.demo.service;

import com.begar.demo.entity.Document;
import com.begar.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getDocuments() {
        return documentRepository.getDocuments();
    }

    public Document getDocument(int id) {
        return documentRepository.getDocument(id);
    }

    public void addDocument(Document document) {
        documentRepository.addDocument(document);
    }

    public String updateDocument(Document document) {
        String result = "";
        if (documentRepository.getDocument(document.getIdDocument()).getIdDocument() == 0) {
            result = "Document don`t exist!";
        } else {
            documentRepository.updateDocument(document);
            result = "Document successfully updated";
        }
        return result;
    }

    public void deleteDocument(int id) {
        documentRepository.deleteDocument(id);
    }
}
