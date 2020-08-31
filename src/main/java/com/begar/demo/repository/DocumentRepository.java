package com.begar.demo.repository;

import com.begar.demo.entity.Document;
import com.begar.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StudentRepository studentRepository;

    public List<Document> getDocuments() {
        List documents = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            documents = session.createQuery("from Document ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return documents;
    }

    public Document getDocument(int id) {
        Document document = new Document();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            document = session.get(Document.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return document;
    }

    public void addDocument(Document document, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = studentRepository.getStudent(id);
            document.setStudent(student);
            session.save(document);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateDocument(Document document) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(document);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteDocument(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Document document = session.get(Document.class, id);
            if (document != null) {
                session.delete(document);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}


