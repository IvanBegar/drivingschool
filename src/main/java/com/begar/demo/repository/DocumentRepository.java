package com.begar.demo.repository;

import com.begar.demo.dto.response.StudentDocumentsDTO;
import com.begar.demo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Query("select new com.begar.demo.dto.response.StudentDocumentsDTO(s.firstName, s.lastName, d.photo, d.mainDocumentsCopies, d.medicalCertificate) from Document d inner join d.student s where s.student_id = :id")
    StudentDocumentsDTO getStudentDocuments(@Param("id") int id);
}
