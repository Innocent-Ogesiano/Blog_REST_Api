package com.example.week9taskinnocentogesiano.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @NotBlank
    private String commentContent;
    private Date dateCommented;
    private Time timeCommented;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    private int likes;
}
