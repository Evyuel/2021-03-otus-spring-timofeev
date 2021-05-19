package ru.dtimofeev.springapp.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @BatchSize(size = 10000)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "bookauthorlink",
            joinColumns = @JoinColumn(name = "authorid"),
            inverseJoinColumns = @JoinColumn(name = "bookid"))
    private Book book;
}
