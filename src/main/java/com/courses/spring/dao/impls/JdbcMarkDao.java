package com.courses.spring.dao.impls;


import com.courses.spring.dao.interfaces.MarkDao;
import com.courses.spring.dao.objects.Mark;
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

@Component("jdbcMarkDao")
public class JdbcMarkDao implements MarkDao {

    static private final String SQL_QUERY_GET_MARK = "select id,student_id,subject_id,mark from mark where id = :id";
    static private final String SQL_QUERY_GET_ALL_MARK = "select id,mark,student_id,subject_id from mark";
    static private final String SQL_QUERY_INSERT_MARK = "insert into mark (student_id,subject_id,mark) values (:student_id,:subject_id,:mark)";
    static private final String SQL_QUERY_DELETE_MARK = "delete from mark where id = :id";
    static private final String SQL_QUERY_UPDATE_MARK = "update mark set student_id = :student_id, subject_id = :subject_id, mark = :mark  where id = :id";

    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Mark getMarktById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_GET_MARK, params, new MarkRowMapper());
    }

    @Override
    public List<Mark> getAllMark() {

        return namedParameterJdbcTemplate.query(SQL_QUERY_GET_ALL_MARK, new MarkRowMapper());
    }

    @Override
    public int insertMark(Mark mark) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("student_id", mark.getStudent_id());
        params.addValue("subject_id", mark.getSubject_id());
        params.addValue("mark", mark.getMark());
        //params.addValue("id", mark.getId());
        namedParameterJdbcTemplate.update(SQL_QUERY_INSERT_MARK, params, keyHolder);
        return keyHolder.getKey().intValue();

    }

    @Override
    public void delateMark(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_MARK, params);
    }

    @Override
    public void updateMark(Mark mark) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("student_id", mark.getStudent_id());
        params.addValue("subject_id", mark.getSubject_id());
        params.addValue("mark", mark.getMark());
        params.addValue("id", mark.getId());
        namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE_MARK, params);

    }

    private static final class MarkRowMapper implements RowMapper<Mark> {

        @Override
        public Mark mapRow(ResultSet rs, int rowNum) throws SQLException {
            Mark mark = new Mark();
            mark.setId(rs.getInt("id"));
            mark.setStudent_id(rs.getInt("student_id"));
            mark.setSubject_id(rs.getInt("subject_id"));
            mark.setMark(rs.getInt("mark"));
            return mark;
        }
    }
}
