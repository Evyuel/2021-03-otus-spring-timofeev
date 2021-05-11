package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookAuthorLinkJdbc implements BookAuthorLinkDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public BookAuthorLinkJdbc(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(BookAuthorLink bookAuthorLink){
        final Map<String,Object> params = new HashMap<>();
        params.put("linkID",bookAuthorLink.getLinkID());
        params.put("bookID",bookAuthorLink.getBookID());
        params.put("authorID",bookAuthorLink.getAuthorID());
        namedParameterJdbcOperations.update("insert into bookauthorlink(linkID,bookID,authorID) values(:linkID,:bookID,:authorID)",params);
    }

    @Override
    public BookAuthorLink getByLinkId(long linkId){
        final Map<String,Object> params = new HashMap<>();
        params.put("linkId",linkId);
        return namedParameterJdbcOperations.queryForObject("select * from bookauthorlink  where linkId=:linkId",params,new BookAuthorLinkMapper());
    }

    @Override
    public List<BookAuthorLink> getBookAuthorLinksByBookID(long bookID){
        final Map<String,Object> params = new HashMap<>();
        params.put("bookID",bookID);
        return namedParameterJdbcOperations.query("select * from bookauthorlink where bookID=:bookID",params,new BookAuthorLinkMapper());
    }

    @Override
    public int getNextSequenceVal(){
        return jdbcOperations.queryForObject("select BookAuthorLink_SQ.nextval from dual",Integer.class);
    }


    private static class BookAuthorLinkMapper implements RowMapper<BookAuthorLink> {

        @Override
        public BookAuthorLink mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookAuthorLink(rs.getLong("linkid"),rs.getLong("bookid"),rs.getLong("authorid"));
        }
    }
}
