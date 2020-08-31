package com.begar.demo.service;

import com.begar.demo.entity.Document;
import com.begar.demo.exception.DataException;
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

    public void addDocument(Document document, int id) {
        documentRepository.addDocument(document, id);
    }

    public void updateDocument(Document document) {
        if (documentRepository.getDocument(document.getDocument_id()).getDocument_id() == 0) {
            throw new DataException("Document don`t exist!");
        } else {
            documentRepository.updateDocument(document);
        }
    }

    public void deleteDocument(int id) {
        documentRepository.deleteDocument(id);
    }
}
