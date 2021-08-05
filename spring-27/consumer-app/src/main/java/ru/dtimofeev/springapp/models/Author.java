package ru.dtimofeev.springapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "author")
@SequenceGenerator(name = "author_sq", initialValue = 11, allocationSize = 1)
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sq")
    private long id;

    @Column(name = "fullname", nullable = false, unique = true)
    private String fullName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
