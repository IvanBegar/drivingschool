package com.begar.demo.repository;

import com.begar.demo.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentRepository {

    @Autowired
    private DataSource dataSource;

    public List<Document> getDocuments() {
        List<Document> documents = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from documents;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Document document = new Document();
                document.setIdDocument(resultSet.getInt(1));
                document.setIdStudent(resultSet.getInt(2));
                document.setPhoto(resultSet.getString(3));
                document.setMainDocumentsCopies(resultSet.getString(4));
                document.setMedicalCertificate(resultSet.getString(5));
                documents.add(document);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public Document getDocument(int id) {
        Document document = new Document();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from documents where idDocuments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                document.setIdDocument(resultSet.getInt(1));
                document.setIdStudent(resultSet.getInt(2));
                document.setPhoto(resultSet.getString(3));
                document.setMainDocumentsCopies(resultSet.getString(4));
                document.setMedicalCertificate(resultSet.getString(5));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void addDocument(Document document) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into documents (idStudent, photo, mainDocumentsCopies, medicalСertificate) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, document.getIdStudent());
            preparedStatement.setString(2, document.getPhoto());
            preparedStatement.setString(3, document.getMainDocumentsCopies());
            preparedStatement.setString(4, document.getMedicalCertificate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDocument(Document document) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update documents set idStudent = ?, photo = ?, mainDocumentsCopies = ?, medicalСertificate = ? where idDocuments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, document.getIdStudent());
            preparedStatement.setString(2, document.getPhoto());
            preparedStatement.setString(3,document.getMainDocumentsCopies());
            preparedStatement.setString(4,document.getMedicalCertificate());
            preparedStatement.setInt(5,document.getIdDocument());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDocument(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from documents where idDocuments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


