package ru.dtimofeev.springapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "bookcomment")
@SequenceGenerator(name = "bookcomment_sq", initialValue = 5, allocationSize = 1)
public class BookComment {

    @Id
    @Column(name = "id")
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcomment_sq")
    private long id;

    @NonNull
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
}