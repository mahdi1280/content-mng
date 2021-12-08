package com.crypto.exchange.contentmng.service.comment;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Comment;
import com.crypto.exchange.contentmng.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public void changeApproved(Comment comment, boolean approved) {
        comment.setApproved(approved);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByCoinInfo(CoinInfo coinInfo) {
        return commentRepository.findByCoinInfo(coinInfo);
    }
}
