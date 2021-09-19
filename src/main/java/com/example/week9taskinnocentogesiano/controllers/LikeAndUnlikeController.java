package com.example.week9taskinnocentogesiano.controllers;

import com.example.week9taskinnocentogesiano.services.LikeAndUnlikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RestController
public class LikeAndUnlikeController {
    private final LikeAndUnlikeServices likeAndUnlikeServices;

    @Autowired
    public LikeAndUnlikeController(LikeAndUnlikeServices likeAndUnlikeServices) {
        this.likeAndUnlikeServices = likeAndUnlikeServices;
    }

    @PostMapping("/likeOrUnlikePost/{pid}")
    public long likeOrUnlikePost(@PathVariable long pid, @RequestParam long uid) {
        boolean status = likeAndUnlikeServices.checkPostLike(uid, pid);
        if (status) {
            likeAndUnlikeServices.unlikePost(uid, pid);
        } else {
            likeAndUnlikeServices.likePost(uid, pid);
        }
        return likeAndUnlikeServices.getAllPostLikes(pid);
    }

    @PostMapping("/likeOrUnlikeComment/{cid}")
    public long likeOrUnlikeComment(@PathVariable long cid, @RequestParam long uid) {
        boolean status = likeAndUnlikeServices.checkCommentLike(uid, cid);
        if (status) {
            likeAndUnlikeServices.unlikeComment(uid, cid);
        } else {
            likeAndUnlikeServices.likeComment(uid, cid);
        }
        return likeAndUnlikeServices.getAllCommentLikes(cid);
    }
}
