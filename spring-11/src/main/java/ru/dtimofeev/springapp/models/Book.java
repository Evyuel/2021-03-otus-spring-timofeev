package ru.dtimofeev.springapp.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "book")
@SequenceGenerator(name = "book_sq", initialValue = 9, allocationSize = 1)
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sq")
    @NonNull
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genreid")
    @NonNull
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "bookauthorlink",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "authorid"))
    private List<Author> authors;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<BookComment> bookComments;

    public Book(@NonNull long id, @NonNull String name, @NonNull Genre genre, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.authors = authors;
    }
}
