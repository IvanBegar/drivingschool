package com.begar.demo.repository;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Group;
import com.begar.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private Transaction transaction;

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private GroupRepository groupRepository;

    public List<Student> getStudents() {
        List students = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            students = session.createQuery("from Student ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudent(int id) {
        Student student = new Student();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    public Student getStudentByPhoneNumber(String phone) {
        Student student = new Student();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Student as s where s.phone = :phone");
            query.setParameter("phone", phone);
            student = (Student) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    public void addStudent(Student student, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Group group = groupRepository.getGroup(id);
            student.setGroup(group);
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Group group = groupRepository.getGroup(id);
            student.setGroup(group);
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<StudentPerCategoryDTO> getStudentsPerCategory(String cat) {
        List studentsPerCategoryDTO = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
//            Query query = session.createQuery("select c.name, g.groupName, s.lastName, s.firstName, s.middleName, s.dateOfBirth, s.phone, s.address from Student s " +
//                    "inner join s.group g " +
//                    "inner join g.category c " +
//                    "where c.name = :name", Object[].class);
//            query.setParameter("name", cat);
//            List<Object[]> results = query.getResultList();
//            for (Object[] row : results) {
//                StudentPerCategoryDTO studentPerCategoryDTO = new StudentPerCategoryDTO();
//                studentPerCategoryDTO.setCategoryName((String) row[0]);
//                studentPerCategoryDTO.setGroupName((String) row[1]);
//                studentPerCategoryDTO.setLastName((String) row[2]);
//                studentPerCategoryDTO.setFirstName((String) row[3]);
//                studentPerCategoryDTO.setMiddleName((String) row[4]);
//                studentPerCategoryDTO.setDateOfBirth((String) row[5]);
//                studentPerCategoryDTO.setPhone((String) row[6]);
//                studentPerCategoryDTO.setAddress((String) row[7]);
//                studentsPerCategoryDTO.add(studentPerCategoryDTO);
//            }
            studentsPerCategoryDTO = session.createSQLQuery("select category.name as categoryName, " +
                    "study_group.groupName as groupName, " +
                    "lastName, firstName, middleName, dateOfBirth, phone, address from student \n" +
                    "inner join study_group on student.group_id=study_group.group_id \n" +
                    "inner join category on study_group.category_id=category.category_id\n" +
                    "where name = ?;")
                    .setParameter(1, cat)
                    .setResultTransformer(Transformers.aliasToBean(StudentPerCategoryDTO.class))
                    .list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentsPerCategoryDTO;
        }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        StudentDocumentsDTO studentDocumentsDTO = new StudentDocumentsDTO();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            studentDocumentsDTO = (StudentDocumentsDTO) session.createSQLQuery("SELECT student.firstName, student.lastName, photo, mainDocumentsCopies, medicalCertificate from document\n" +
                    "inner join student on document.student_id=student.student_id\n" +
                    "where document.student_id = ?;")
                    .setParameter(1, id)
                    .setResultTransformer(Transformers.aliasToBean(StudentDocumentsDTO.class))
                    .getSingleResult();
            } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentDocumentsDTO;
    }

    public List<Student> getStudentsPerGroup(int id) {
        String query = "select * from student \n" +
                    "inner join mydb.group on student.group_id=mydb.group.group_id \n" +
                    "where mydb.group.group_id = ?;";
        List students = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            students = session.createQuery("from Student s inner join s.group g where g.group_id = :id")
                    .setParameter("id", id)
                    .list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }
}
