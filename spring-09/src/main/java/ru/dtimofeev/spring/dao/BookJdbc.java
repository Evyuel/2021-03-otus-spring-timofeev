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
    public void insert(Book book){
        final Map<String,Object> params = new HashMap<>();
        params.put("id",book.getId());
        params.put("name",book.getName());
        namedParameterJdbcOperations.update("insert into book(id,name) values(:id,:name)",params);
    }
    @Override
    public void updateById(Book book){
        final Map<String,Object> params = new HashMap<>();
        params.put("id",book.getId());
        params.put("name",book.getName());
        namedParameterJdbcOperations.update("update book set name=:name where id=:id",params);
    }
    @Override
    public void deleteById(long id){
        final Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        namedParameterJdbcOperations.update("delete book where id=:id",params);
    }
    @Override
    public Book getById(long id){
        final Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        return namedParameterJdbcOperations.queryForObject("select id,name from book where id=:id",params,new BookMapper());
    }

    @Override
    public List<Book> getAll(){
        return jdbcOperations.query("select id,name from book",new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Book(id,name);
        }
    }
}
