package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dtimofeev.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreJdbc implements GenreDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public GenreJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        namedParameterJdbcOperations.update("insert into genre(id,name) values(:id,:name)", params);
    }

    @Override
    public void updateById(Genre genre) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        namedParameterJdbcOperations.update("update genre set name=:name where id=:id", params);
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterJdbcOperations.update("delete genre where id=:id", params);
    }

    @Override
    public Genre getById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcOperations.queryForObject("select id,name from genre where id=:id", params, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return namedParameterJdbcOperations.queryForObject("select id,name from genre where name=:name", params, new GenreMapper());
    }

    @Override
    public int getNextSequenceVal() {
        return jdbcOperations.queryForObject("select genre_sq.nextval from dual", Integer.class);
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("select id,name from genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
