package ru.dtimofeev.springapp.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "genreid")
    @NonNull
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
    //@JoinTable(name = "bookauthorlink", joinColumns = @JoinColumn(name = "bookid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    private List<Author> authors;

    //@Fetch(FetchMode.SUBSELECT)
    //@OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    //@JoinColumn(name = "bookid",nullable = false)
   // private List<BookComment> bookComments;
}
