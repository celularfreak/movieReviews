package com.MovieReviews.moviereviews.model;


import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private int karma;


    public User(@NonNull String email, @NonNull String name, @NonNull String password, int karma) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.karma = karma;
    }
}
