package com.begar.demo.controller;

import com.begar.demo.entity.Document;
import com.begar.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/documents")
    public List<Document> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping("/documents/{id}")
    public Document getDocument(@PathVariable int id) {
        return documentService.getDocument(id);
    }

    @PostMapping("/documents")
    public void addDocument(@RequestBody Document document) {
        documentService.addDocument(document);
    }

    @PutMapping("/documents")
    public String updateDocument(@RequestBody Document document) {
        return documentService.updateDocument(document);
    }

    @PatchMapping("/documents/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Document document = documentService.getDocument(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Document.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, document, v);
        });
        documentService.updateDocument(document);
    }

    @DeleteMapping("/documents/{id}")
    public void deleteDocument(@PathVariable int id) {
        documentService.deleteDocument(id);
    }
}
