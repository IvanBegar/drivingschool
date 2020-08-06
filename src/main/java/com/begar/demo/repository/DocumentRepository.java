package com.begar.demo.repository;

import com.begar.demo.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DocumentRepository {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Document> getDocuments() {
        String query = "select * from document;";
        return jdbcTemplate.query(query, getDocumentRowMapper());
    }

    public Document getDocument(int id) {
        String query = "select * from document where document_id = ?;";
        return jdbcTemplate.queryForObject(query, getDocumentRowMapper(), id);
    }

    public void addDocument(Document document, int id) {
        String query = "insert into document (student_id, photo, mainDocumentsCopies, medicalCertificate) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, document.getPhoto(), document.getMainDocumentsCopies(), document.getMedicalCertificate());
    }

    public void updateDocument(Document document, int id) {
        String query = "update document set student_id = ?, photo = ?, mainDocumentsCopies = ?, medicalCertificate = ? where document_id = ?;";
        jdbcTemplate.update(query, id, document.getPhoto(), document.getMainDocumentsCopies(), document.getMedicalCertificate(), document.getDocument_id());
    }

    public void deleteDocument(int id) {
        String query = "delete from document where document_id = ?;";
        jdbcTemplate.update(query, id);
    }

    private RowMapper<Document> getDocumentRowMapper() {
        return (resultSet, i) -> {
            Document document = new Document();
            document.setDocument_id(resultSet.getInt("document_id"));
            document.setStudent(studentRepository.getStudent(resultSet.getInt("student_id")));
            document.setPhoto(resultSet.getString("photo"));
            document.setMainDocumentsCopies(resultSet.getString("mainDocumentsCopies"));
            document.setMedicalCertificate(resultSet.getString("medicalCertificate"));
            return document;
        };
    }
}


