package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCoinInfo(CoinInfo coinInfo);
}
