package ru.dtimofeev.springapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookcomment")
@SequenceGenerator(name = "bookcomment_sq", initialValue = 5, allocationSize = 1)
public class BookComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcomment_sq")
    private long id;

    @Column(name = "commenttext", nullable = false)
    private String commentText;
}