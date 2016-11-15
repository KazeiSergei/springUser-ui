package com.courses.spring.dao.interfaces;




import com.courses.spring.dao.objects.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> getAll();
    public Student getStudentById(int id);
    public List<Student> getStudentWithMark(int id);
    public int insertStudent(Student student);
    public void delateStudent(int id);
    public void updateStudent(Student student);
}
