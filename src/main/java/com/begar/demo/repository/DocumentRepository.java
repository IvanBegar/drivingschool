package com.begar.demo.repository;

import com.begar.demo.dto.StudentDTO;
import com.begar.demo.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Document> getDocuments() {
        String query = "select * from documents;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Document document = new Document();
            document.setIdDocument(resultSet.getInt("idDocuments"));
            document.setStudent(getStudentDTO(resultSet.getInt("idStudent")));
            document.setPhoto(resultSet.getString("photo"));
            document.setMainDocumentsCopies(resultSet.getString("mainDocumentsCopies"));
            document.setMedicalCertificate(resultSet.getString("medicalCertificate"));
            return document;
        });
    }

    public Document getDocument(int id) {
        String query = "select * from documents where idDocuments = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
            Document document = new Document();
            document.setIdDocument(resultSet.getInt("idDocuments"));
            document.setStudent(getStudentDTO(resultSet.getInt("idStudent")));
            document.setPhoto(resultSet.getString("photo"));
            document.setMainDocumentsCopies(resultSet.getString("mainDocumentsCopies"));
            document.setMedicalCertificate(resultSet.getString("medicalCertificate"));
            return document;
        });
    }

    public void addDocument(Document document, int id) {
        String query = "insert into documents (idStudent, photo, mainDocumentsCopies, medicalСertificate) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, document.getPhoto(), document.getMainDocumentsCopies(), document.getMedicalCertificate());
    }

    public void updateDocument(Document document, int id) {
        String query = "update documents set idStudent = ?, photo = ?, mainDocumentsCopies = ?, medicalСertificate = ? where idDocuments = ?;";
        jdbcTemplate.update(query, id, document.getPhoto(), document.getMainDocumentsCopies(), document.getMedicalCertificate(), document.getIdDocument());
    }

    public void deleteDocument(int id) {
        String query = "delete from documents where idDocuments = ?;";
        jdbcTemplate.update(query, id);
    }

    public StudentDTO getStudentDTO(int id) {
        String query = "select lastName, firstName, middleName, phone from student " +
                "inner join documents on student.idStudent=documents.idStudent " +
                "where student.idStudent = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(resultSet.getString("firstName"));
            studentDTO.setMiddleName(resultSet.getString("middleName"));
            studentDTO.setLastName(resultSet.getString("lastName"));
            studentDTO.setPhone(resultSet.getString("phone"));
            return studentDTO;
        });
    }
}


