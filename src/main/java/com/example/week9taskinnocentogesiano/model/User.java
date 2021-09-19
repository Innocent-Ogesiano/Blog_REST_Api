package com.example.week9taskinnocentogesiano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blog_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    @NotNull
    private String username;
    @Column(unique = true)
    @NotNull
    private String email;
    private String role;
    @Size(min = 5, message = "Password should contain at least 5 characters")
    private String password;
    private Date dateRegistered;
    @ManyToMany
    private Set<Post> listOfFavouritePosts;
    @JsonIgnore
    @OneToOne
    private Connection connections;
    @Column(columnDefinition = "boolean default false")
    private boolean deleteAccount;
}
