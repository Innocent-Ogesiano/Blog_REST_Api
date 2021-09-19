package com.example.week9taskinnocentogesiano.repositories;

import com.example.week9taskinnocentogesiano.model.LikeAndUnlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeAndUnlikeRepository extends JpaRepository<LikeAndUnlike, Long> {
    List<LikeAndUnlike> findAllByPost_PostId(long pid);
    List<LikeAndUnlike> findAllByComment_CommentId(long cid);
    LikeAndUnlike findByUser_UserIdAndPost_PostId(Long uid, Long pid);
    LikeAndUnlike findByUser_UserIdAndComment_CommentId(Long uid, Long cid);
}
