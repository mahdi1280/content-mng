package com.gd.contentmng.service.comment;

import com.gd.contentmng.model.Comment;

import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void changeApproved(Comment comment,boolean approved);
}
