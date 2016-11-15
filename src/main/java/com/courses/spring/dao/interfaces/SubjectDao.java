package com.courses.spring.dao.interfaces;


import com.courses.spring.dao.objects.Subject;

import java.util.List;

public interface SubjectDao {

    Subject getSubjectById(int id);
    List<Subject> getAllSudject();
    int insertSubject(Subject subject);
    void insertListSubject(List<Subject> subjects);
    void delateSubject(int id);
    void delateSubject(Subject subject);
    void updateSubject(Subject subject, int id);

}
