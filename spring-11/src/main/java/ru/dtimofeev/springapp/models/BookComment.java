package ru.dtimofeev.springapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "bookcomment")
@SequenceGenerator(name = "bookcomment_sq", initialValue = 5, allocationSize = 1)
public class BookComment {

    @Id
    @Column(name = "id")
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcomment_sq")
    private long id;

    @NonNull
    @Column(name = "commenttext", nullable = false)
    private String commentText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;
}