package com.courses.spring.interfaces;


import com.courses.spring.objects.Mark;

import java.util.List;

public interface MarkDao {

    public Mark getMarktById(int id);
    public List<Mark> getAllMark();
    public int insertMark(Mark mark);
    public void delateMark(int id);
    public void updateMark(Mark mark);

}
