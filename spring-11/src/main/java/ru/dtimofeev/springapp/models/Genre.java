package ru.dtimofeev.springapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "genre")
@SequenceGenerator(name = "genre_sq", initialValue = 4, allocationSize = 1)
public class Genre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_sq")
    @NonNull
    private long id;
    @Column(name = "name", nullable = false, unique = true)
    @NonNull
    private String name;
}
