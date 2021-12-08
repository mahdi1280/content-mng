package com.crypto.exchange.contentmng.service.comment;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void changeApproved(Comment comment, boolean approved);

    List<Comment> findByCoinInfo(CoinInfo coinInfo);
}
