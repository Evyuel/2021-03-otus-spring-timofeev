package ru.dtimofeev.springapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bookcomment")
@SequenceGenerator(name = "bookcomment_sq", initialValue = 5, allocationSize = 1)
public class BookComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcomment_sq")
    private long id;

    @Column(name = "commenttext", nullable = false)
    private String commentText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookid")
    private Book book;

    public BookComment(@NonNull long id, @NonNull String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public BookComment(long id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }
}