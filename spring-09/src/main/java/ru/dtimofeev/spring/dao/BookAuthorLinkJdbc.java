package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public void insert(BookAuthorLink bookAuthorLink) {
        final Map<String, Object> params = new HashMap<>();
        params.put("linkID", bookAuthorLink.getLinkID());
        params.put("bookID", bookAuthorLink.getBookID());
        params.put("authorID", bookAuthorLink.getAuthorID());
        namedParameterJdbcOperations.update("insert into bookauthorlink(linkID,bookID,authorID) values(:linkID,:bookID,:authorID)", params);
    }

    @Override
    public void deleteByBookId(long bookId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        namedParameterJdbcOperations.update("delete bookauthorlink where bookId=:bookId", params);
    }

    @Override
    public BookAuthorLink getByLinkId(long linkId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("linkId", linkId);
        return namedParameterJdbcOperations.queryForObject("select linkid,bookid,authorid from bookauthorlink  where linkId=:linkId", params, new BookAuthorLinkMapper());
    }

    @Override
    public List<BookAuthorLink> getBookAuthorLinksByBookID(long bookID) {
        final Map<String, Object> params = new HashMap<>();
        params.put("bookID", bookID);
        return namedParameterJdbcOperations.query("select linkid,bookid,authorid from bookauthorlink where bookID=:bookID", params, new BookAuthorLinkMapper());
    }

    @Override
    public Map<Long, List<Author>> getAuthorsOfAllBooks() {
        Map<Long, List<Author>> m = new HashMap<>();
        return jdbcOperations.query("SELECT bal.bookid,a.id authorid,a.fullname authorFullName FROM BOOKAUTHORLINK bal\n" +
                "                  join author a on a.id=bal.authorid", new ResultSetExtractor<Map<Long, List<Author>>>() {
            @Override
            public Map<Long, List<Author>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    long bookId = rs.getLong("bookid");
                    long authorId = rs.getLong("authorid");
                    String authorFullName = rs.getString("authorFullName");

                    if (m.containsKey(bookId)) {
                        m.get(bookId).add(new Author(authorId, authorFullName));
                    } else {
                        m.put(bookId, new ArrayList<>(Arrays.asList(new Author(authorId, authorFullName))));
                    }
                }
                return m;
            }
        });
    }

    @Override
    public List<Long> getAuthorIDListByBookID(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);
        return namedParameterJdbcOperations.query("select authorid from bookauthorlink where bookId=:bookId", params, new ResultSetExtractor<List<Long>>() {
            @Override
            public List<Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Long> l = new ArrayList<>();
                while (rs.next()) {
                    l.add(rs.getLong(1));
                }
                return l;
            }
        });
    }

    @Override
    public int getNextSequenceVal() {
        return jdbcOperations.queryForObject("select BookAuthorLink_SQ.nextval from dual", Integer.class);
    }


    private static class BookAuthorLinkMapper implements RowMapper<BookAuthorLink> {

        @Override
        public BookAuthorLink mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookAuthorLink(rs.getLong("linkid"), rs.getLong("bookid"), rs.getLong("authorid"));
        }
    }
}
