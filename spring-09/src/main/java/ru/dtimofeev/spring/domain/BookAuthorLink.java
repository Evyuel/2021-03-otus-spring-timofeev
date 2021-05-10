package ru.dtimofeev.spring.domain;

public class BookAuthorLink {
    private final long linkID;
    private final long bookID;
    private final long authorID;

    public BookAuthorLink(long linkID, long bookID, long authorID) {
        this.linkID = linkID;
        this.bookID = bookID;
        this.authorID = authorID;
    }

    public long getLinkID() {
        return linkID;
    }

    public long getBookID() {
        return bookID;
    }

    public long getAuthorID() {
        return authorID;
    }
}
