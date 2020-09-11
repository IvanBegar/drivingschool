package com.begar.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher", schema = "hibernate_db")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacher_id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "salary")
    private double salary;

    @JsonIgnoreProperties("teachers")
    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_group",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

    public Teacher(int teacher_id, String firstName, String middleName, String lastName, String dateOfBirth, String address, String phone, double salary, List<Group> groups) {
        this.teacher_id = teacher_id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.groups = groups;
    }

    public Teacher() {
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int idStudent) {
        this.teacher_id = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", groups=" + groups +
                '}';
    }
}
