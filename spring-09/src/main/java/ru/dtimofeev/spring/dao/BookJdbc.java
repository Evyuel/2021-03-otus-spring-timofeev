package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dtimofeev.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookJdbc implements BookDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public BookJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("genreID", book.getGenreID());
        namedParameterJdbcOperations.update("insert into book(id,name,genreID) values(:id,:name,:genreID)", params);
    }

    @Override
    public void updateById(Book book) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("genreID", book.getGenreID());
        namedParameterJdbcOperations.update("update book set name=:name,genreID=:genreID where id=:id", params);
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterJdbcOperations.update("delete book where id=:id", params);
    }

    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from book where id=:id", params, new BookMapper());
    }

    @Override
    public List<Book> getByGenreID(long genreID) {
        final Map<String, Object> params = new HashMap<>();
        params.put("genreID", genreID);
        return namedParameterJdbcOperations.query("select * from book where genreID=:genreID", params, new BookMapper());
    }

    @Override
    public Book getByName(String name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return namedParameterJdbcOperations.queryForObject("select * from book where name=:name", params, new BookMapper());
    }

    @Override
    public int getNextSequenceVal() {
        return jdbcOperations.queryForObject("select book_sq.nextval from dual", Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select * from book", new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long genreID = resultSet.getLong("genreID");
            return new Book(id, name, genreID);
        }
    }
}
