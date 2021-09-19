package com.example.week9taskinnocentogesiano.repositories;

import com.example.week9taskinnocentogesiano.model.Post;
import com.example.week9taskinnocentogesiano.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
}
