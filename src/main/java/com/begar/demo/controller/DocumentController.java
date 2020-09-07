package com.begar.demo.controller;

import com.begar.demo.dto.response.StudentDocumentsDTO;
import com.begar.demo.entity.Document;
import com.begar.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/documents")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Document> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable int id) {
        return documentService.getDocument(id);
    }

    @PostMapping("/student-id={id}")
    public void addDocument(@RequestBody Document document, @PathVariable int id) {
        documentService.addDocument(document, id);
    }

    @PutMapping
    public void updateDocument(@RequestBody Document document) {
        documentService.updateDocument(document);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Document document = documentService.getDocument(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Document.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, document, v);
        });
        documentService.updateDocument(document);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable int id) {
        documentService.deleteDocument(id);
    }

    @RequestMapping("/student-documents/{id}")
    public StudentDocumentsDTO getStudentDocuments(@PathVariable int id) {
        return documentService.getStudentDocuments(id);
    }
}

