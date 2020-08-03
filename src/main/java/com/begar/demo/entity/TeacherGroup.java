package com.begar.demo.entity;

public class TeacherGroup {

    private int idTeacherGroup;
    private int idTeacher;
    private int idGroup;

    @Override
    public String toString() {
        return "TeacherGroup{" +
                "idTeacheGroup=" + idTeacherGroup +
                ", idTeacher=" + idTeacher +
                ", idGroup=" + idGroup +
                '}';
    }

    public int getIdTeacherGroup() {
        return idTeacherGroup;
    }

    public void setIdTeacherGroup(int idTeacherGroup) {
        this.idTeacherGroup = idTeacherGroup;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
