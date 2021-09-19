package com.example.week9taskinnocentogesiano.services;

import org.springframework.stereotype.Service;

@Service
public interface LikeAndUnlikeServices {
    long likePost(long uid, long pid);
    long unlikePost(long uid, long pid);
    long likeComment(long uid, long cid);
    long unlikeComment(long uid, long cid);
    boolean checkPostLike(long uid, long pid);
    boolean checkCommentLike(long uid, long cid);
    long getAllCommentLikes(long cid);
    long getAllPostLikes(long pid);
}
