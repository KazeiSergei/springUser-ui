package com.courses.spring.dao.objects;


import javax.validation.constraints.Pattern;

public class Student {

    private int id;

    @Pattern(regexp="^[A-Z][a-z]{1,255}$",message = "Name is invalid")
    private String firstName;

    @Pattern(regexp="^[A-Z][a-z]{1,255}$",message = "SecondName is invalid")
    private String secondName;
    private int mark;
    private String subject;
    private int markId;



    public Student() {

    }
    public Student(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Student (int id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    public Student (int id, String firstName, String secondName, String subject, int mark, int markId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.mark = mark;
        this.subject =subject;
        this.markId = markId;

    }
    @Override
    public String toString() {

        if (subject == null){
            return "Student(" + "id=" + getId() + ", first_name=" + getFirstName() + ", second_name=" + getSecondName() + '\'' +')';
        }
        return "Student(" + "id=" + getId() + ", first_name=" + getFirstName() + ", second_name=" + getSecondName() +", subject=" + getSubject() +", mark=" + getMark() + '\'' +')';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

}
