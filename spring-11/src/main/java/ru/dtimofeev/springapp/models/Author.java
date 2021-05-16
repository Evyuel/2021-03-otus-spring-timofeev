package ru.dtimofeev.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
@SequenceGenerator(name = "author_sq",initialValue = 11,allocationSize = 1)
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_sq")
    private long id;
    @Column(name = "fullname", nullable = false, unique = true)
    private String fullName;
}
