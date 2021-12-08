package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.CommentAddRequest;
import com.crypto.exchange.contentmng.dto.response.CommentAddResponse;
import com.crypto.exchange.contentmng.dto.response.CommentResponse;
import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Comment;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import com.crypto.exchange.contentmng.service.comment.CommentService;
import com.gd.auth.client.SecurityUtil;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CoinInfoService coinInfoService;
    private final MessageSourceAccessor messageSourceAccessor;

    public CommentController(CommentService commentService, CoinInfoService coinInfoService, MessageSourceAccessor messageSourceAccessor) {
        this.commentService = commentService;
        this.coinInfoService = coinInfoService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @PostMapping
    public ResponseEntity<CommentAddResponse> save(@Valid @RequestBody CommentAddRequest commentAddRequest) {
        CoinInfo coinInfo = coinInfoService.findById(commentAddRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.comment.find.coin"), "save.comment.find.coin")));
        Comment parentComment = null;
        if (commentAddRequest.getParentId() > 0) {
            parentComment = commentService.findById(commentAddRequest.getParentId()).orElseThrow(
                    () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.comment.parent.find"), "save.comment.parent.find")));
        }
        Comment comment = commentService.save(setComment(commentAddRequest, coinInfo, parentComment));
        return ResponseEntity.ok().body(new CommentAddResponse(comment.getId()));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id:\\d}/{approved:(?:true|false)}")
    public ResponseEntity<Object> changeApproved(@PathVariable int id, @PathVariable boolean approved) {
        Comment comment = commentService.findById(id).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("change.approved.find"), "change.approved.find")));
        commentService.changeApproved(comment, approved);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id:\\d}")
    public ResponseEntity<List<CommentResponse>> getComment(@PathVariable long id) {
        CoinInfo coinInfo = coinInfoService.findById(id).orElseThrow(() ->
                new RuleException(ErrorMessage.error("comment.find.coin.info", "comment.find.coin.info")));
        List<Comment> comments = commentService.findByCoinInfo(coinInfo);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setNickName(comment.getNickName());
            commentResponse.setText(comment.getText());
            commentResponse.setUserId(comment.getUserId());
            commentResponse.setId(comment.getId());
            commentResponse.setParent(comment.getParent() == null? 0 : comment.getParent().getId());
            commentResponses.add(commentResponse);
        }
        return ResponseEntity.ok(commentResponses);
    }

    private Comment setComment(CommentAddRequest commentAddRequest, CoinInfo coinInfo, Comment parentComment) {
        Comment comment = Comment.builder()
                .setCoinInfo(coinInfo)
                .setParent(parentComment)
                .setText(commentAddRequest.getText())
                .setUserId(SecurityUtil.getCurrentUserId() == null ? 0 : SecurityUtil.getCurrentUserId())
                .build();
        if (commentAddRequest.getNikName() == null || commentAddRequest.getNikName().equals("")) {
            if (SecurityUtil.getCurrentUserId() == null)
                comment.setNickName("Anonymous");
        } else {
            comment.setNickName(commentAddRequest.getNikName());
        }
        return comment;
    }
}
