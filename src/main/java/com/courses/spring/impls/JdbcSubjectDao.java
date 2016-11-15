package com.courses.spring.impls;


import com.courses.spring.interfaces.SubjectDao;
import com.courses.spring.objects.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component("jdbcSubjectDao")
public class JdbcSubjectDao implements SubjectDao {

    private NamedParameterJdbcOperations namedParameterJdbcTemplate;
    static private  final String SQL_QUERY_GET_SUBJECT = "select id,subject_name from subject where id = :id";
    static private  final String SQL_QUERY_GET_ALL_SUBJECT = "select id,subject_name from subject";
    static private final String SQL_QUERY_INSERT_SUBJECT = "insert into subject (subject_name) values (:subject_name)";
    static private final String SQL_QUERY_DELETE_SUBJECT = "delete from subject where id =:id";
    static private final String SQL_QUERY_UPDATE_SUBJECT = "update subject set subject_name =:subject_name where id = :id";

    @Override
    public List<Subject> getAllSudject() {

        return namedParameterJdbcTemplate.query(SQL_QUERY_GET_ALL_SUBJECT, new SubjectRowMapper());
    }

    @Override
    public int insertSubject(Subject subject) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("subject_name", subject.getName());
        namedParameterJdbcTemplate.update(SQL_QUERY_INSERT_SUBJECT,params,keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void insertListSubject(List<Subject> subjects) {
        for(Subject s:subjects){
            insertSubject(s);
        }

    }

    @Override
    public void delateSubject(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_SUBJECT,params);
    }

    @Override
    public void delateSubject(Subject subject) {
        delateSubject(subject.getId());
    }

    @Override
    public void updateSubject(Subject subject, int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("subject_name",subject.getName());
        params.addValue("id",id);
        namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE_SUBJECT, params);

    }


    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Subject getSubjectById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_GET_SUBJECT,params,new SubjectRowMapper());
    }

    private static final class SubjectRowMapper implements RowMapper<Subject> {
        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subject subject= new Subject();
            subject.setId(rs.getInt("id"));
            subject.setName(rs.getString("subject_name"));
            return subject;
        }
    }
}
