package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.model.Comment;
import com.example.week9taskinnocentogesiano.model.LikeAndUnlike;
import com.example.week9taskinnocentogesiano.model.Post;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.CommentRepository;
import com.example.week9taskinnocentogesiano.repositories.LikeAndUnlikeRepository;
import com.example.week9taskinnocentogesiano.repositories.PostRepository;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import com.example.week9taskinnocentogesiano.services.LikeAndUnlikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeAndUnlikeServiceImpl implements LikeAndUnlikeServices {
    private final LikeAndUnlikeRepository likeAndUnlikeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeAndUnlikeServiceImpl(LikeAndUnlikeRepository likeAndUnlikeRepository, PostRepository postRepository,
                                    CommentRepository commentRepository, UserRepository userRepository) {
        this.likeAndUnlikeRepository = likeAndUnlikeRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public long likePost(long uid, long pid) {
        LikeAndUnlike like = new LikeAndUnlike();
        User user = userRepository.getById(uid);
        Post post = postRepository.getById(pid);
        like.setPost(post);
        like.setUser(user);
        likeAndUnlikeRepository.save(like);
        post.setNoOfLikes((int) getAllPostLikes(pid));
        postRepository.save(post);
        return getAllPostLikes(pid);
    }

    @Override
    public long unlikePost(long uid, long pid) {
        LikeAndUnlike like = likeAndUnlikeRepository.findByUser_UserIdAndPost_PostId(uid, pid);
        if (like != null) {
            likeAndUnlikeRepository.delete(like);
            Post post = postRepository.getById(pid);
            post.setNoOfLikes((int) getAllPostLikes(pid));
            postRepository.save(post);
        }
        return getAllPostLikes(pid);
    }

    @Override
    public long likeComment(long uid, long cid) {
        LikeAndUnlike like = new LikeAndUnlike();
        User user = userRepository.getById(uid);
        Comment comment = commentRepository.getById(cid);
        like.setComment(comment);
        like.setUser(user);
        likeAndUnlikeRepository.save(like);
        comment.setLikes((int) getAllCommentLikes(cid));
        commentRepository.save(comment);
        return getAllCommentLikes(cid);
    }

    @Override
    public long unlikeComment(long uid, long cid) {
        LikeAndUnlike like = likeAndUnlikeRepository.findByUser_UserIdAndComment_CommentId(uid, cid);
        if (like != null) {
            likeAndUnlikeRepository.delete(like);
            Comment comment = commentRepository.getById(cid);
            comment.setLikes((int) getAllCommentLikes(cid));
            commentRepository.save(comment);
        }
        return getAllCommentLikes(cid);
    }

    @Override
    public boolean checkPostLike(long uid, long pid) {
        return likeAndUnlikeRepository.findByUser_UserIdAndPost_PostId(uid, pid) != null;
    }

    @Override
    public boolean checkCommentLike(long uid, long cid) {
        return likeAndUnlikeRepository.findByUser_UserIdAndComment_CommentId(uid, cid) != null;
    }

    @Override
    public long getAllCommentLikes(long cid) {
        return likeAndUnlikeRepository.findAllByComment_CommentId(cid).size();
    }

    @Override
    public long getAllPostLikes(long pid) {
        return likeAndUnlikeRepository.findAllByPost_PostId(pid).size();
    }
}
