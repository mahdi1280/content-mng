package com.gd.contentmng.controller;

import com.gd.contentmng.dto.request.CommentAddRequest;
import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Comment;
import com.gd.contentmng.service.coininfo.CoinInfoService;
import com.gd.contentmng.service.comment.CommentService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Object> save(@Valid @RequestBody CommentAddRequest commentAddRequest) {
        CoinInfo coinInfo=coinInfoService.findById(commentAddRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.comment.find.coin"), "save.comment.find.coin")));
        Comment parentComment=null;
        if(commentAddRequest.getParentId()>0){
            parentComment = commentService.findById(commentAddRequest.getParentId()).orElseThrow(
                    ()->new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.comment.parent.find"),"save.comment.parent.find")));}
        commentService.save(setComment(commentAddRequest,coinInfo,parentComment));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id:\\d}/{approved:(?:true|false)}")
    public ResponseEntity<Object> changeApproved(@PathVariable int id,@PathVariable boolean approved){
        Comment comment = commentService.findById(id).orElseThrow(
                ()->new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("change.approved.find"),"change.approved.find")));
        commentService.changeApproved(comment,approved);
        return ResponseEntity.ok().build();
    }

    private Comment setComment(CommentAddRequest commentAddRequest,CoinInfo coinInfo,Comment parentComment) {
        Comment comment = Comment.builder()
                .setCoinInfo(coinInfo)
                .setParent(parentComment)
                .setText(commentAddRequest.getText())
                .setUserId(0)
                .build();
        if(commentAddRequest.getNikName() == null || commentAddRequest.getNikName().equals("")){
            comment.setNikName("Anonymous");
        }else{
            comment.setNikName(commentAddRequest.getNikName());
        }
        return comment;
    }
}
