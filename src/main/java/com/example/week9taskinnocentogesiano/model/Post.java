package com.example.week9taskinnocentogesiano.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @NotBlank
    private String postContent;
    private Date datePosted;
    private Time timePosted;
    @JsonIgnore
    @NotNull
    @ManyToOne
    private User user;
    private int noOfLikes;
    private int noOfComments;

}
