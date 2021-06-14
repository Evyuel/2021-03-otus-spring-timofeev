package ru.dtimofeev.springapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "author")
@SequenceGenerator(name = "author_sq", initialValue = 11, allocationSize = 1)
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sq")
    @NonNull
    private long id;
    @NonNull
    @Column(name = "fullname", nullable = false, unique = true)
    private String fullName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
