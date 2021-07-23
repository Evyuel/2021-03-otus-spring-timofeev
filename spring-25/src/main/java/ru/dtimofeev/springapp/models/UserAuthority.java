package ru.dtimofeev.springapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userauthorities")
public class UserAuthority {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
