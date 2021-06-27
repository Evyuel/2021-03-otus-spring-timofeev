package ru.dtimofeev.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genre")
@SequenceGenerator(name = "genre_sq", initialValue = 4, allocationSize = 1)
public class Genre {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sq")
    private long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
