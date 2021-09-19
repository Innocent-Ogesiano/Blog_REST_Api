package com.example.week9taskinnocentogesiano.repositories;

import com.example.week9taskinnocentogesiano.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost_PostId(long pid);
}
