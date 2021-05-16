package ru.dtimofeev.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;

    //@Column(name = "Genreid",nullable = false)
    //@ManyToOne(fetch = FetchType.LAZY)
    //private Genre Genre;
}
