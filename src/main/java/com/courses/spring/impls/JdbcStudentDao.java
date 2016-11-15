package com.courses.spring.impls;


import com.courses.spring.interfaces.StudentDao;
import com.courses.spring.objects.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("jdbcStudentDao")
public class JdbcStudentDao implements StudentDao {


    private static final String SQL_QUERY_INSERT_STUDENT = "insert into student (first_name,second_name) values (:first_name,:second_name)";
    private static final String SQL_QUERY_GET_STUDENT_BY_ID = "select id,first_name,second_name from student where id = :id";
    private static final String SQL_QUERY_UDATE_STUDENT = "update student set first_name = :first_name,second_name = :second_name where id = :id";
    private static final String SQL_QUERY_DELETE_STUDENT = "delete from student where id = :id";
    private static final String SQL_QUERY_GET_STUDENT_WITH_MARKS = "select student.id,student.first_name,student.second_name,subject.subject_name,mark.mark,mark.id from student inner join mark on student.id = mark.student_id inner join subject on mark.subject_id = subject.id where student.id = :id";
    private static final String SQL_QUERY_GET_ALL_STUDENT = "select id,first_name,second_name from student";
    NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Student> getAll() {
        return namedParameterJdbcTemplate.query(SQL_QUERY_GET_ALL_STUDENT, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_GET_STUDENT_BY_ID, params, new StudentRowMapper());
    }

    @Override
    public List<Student> getStudentWithMark(int id) {
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("id",id);
        return namedParameterJdbcTemplate.query(SQL_QUERY_GET_STUDENT_WITH_MARKS,params, new ResultSetExtractor<List<Student>>() {
            @Override
            public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Student> list = new ArrayList<Student>();
                while(rs.next()){
                    int id = rs.getInt("student.id");
                    String first_name = rs.getString("student.first_name");
                    String second_name = rs.getString("student.second_name");
                    String subject = rs.getString("subject.subject_name");
                    int mark = rs.getInt("mark.mark");
                    int markId = rs.getInt("mark.id");
                    list.add(new Student(id, first_name, second_name, subject, mark,markId));
                }
                return list;
            }
        });
    }

    @Override
    public int insertStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", student.getId());
        params.addValue("first_name", student.getFirstName());
        params.addValue("second_name", student.getSecondName());
        namedParameterJdbcTemplate.update(SQL_QUERY_INSERT_STUDENT, params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delateStudent(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_STUDENT, params);
    }

    @Override
    public void updateStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", student.getId());
        params.addValue("first_name", student.getFirstName());
        params.addValue("second_name", student.getSecondName());
        namedParameterJdbcTemplate.update(SQL_QUERY_UDATE_STUDENT, params);
    }

    private static final class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setSecondName(rs.getString("second_name"));
            return student;
        }
    }
}
