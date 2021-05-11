package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dtimofeev.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorJdbc implements AuthorDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AuthorJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("fullName", author.getFullName());
        namedParameterJdbcOperations.update("insert into author(id,fullName) values(:id,:fullName)", params);
    }

    @Override
    public void updateById(Author author) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("fullName", author.getFullName());
        namedParameterJdbcOperations.update("update author set fullName=:fullName where id=:id", params);
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterJdbcOperations.update("delete author where id=:id", params);
    }

    @Override
    public Author getById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from author where id=:id", params, new AuthorMapper());
    }

    @Override
    public Author getByFullName(String fullName) {
        final Map<String, Object> params = new HashMap<>();
        params.put("fullName", fullName);
        return namedParameterJdbcOperations.queryForObject("select * from author where fullName=:fullName", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query("select * from author", new AuthorMapper());
    }

    @Override
    public int getNextSequenceVal() {
        return jdbcOperations.queryForObject("select author_sq.nextval from dual", Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String fullName = resultSet.getString("fullName");
            return new Author(id, fullName);
        }
    }
}
