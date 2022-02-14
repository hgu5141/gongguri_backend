package com.example.gongguri.controller;



import com.example.gongguri.dto.CommentRequestDto;
import com.example.gongguri.dto.CommentResponseDto;
import com.example.gongguri.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


//    코멘트 작성 - 로그인 되어 있으면 작성가능, 아니면 안됨. void ->
    @PostMapping("/api/{postId}/comments")
    public void createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto){
        commentService.save(postId, commentRequestDto);
    }

//                코멘트 조회
    @GetMapping("/api/{postId}/comments")
    public List<CommentResponseDto> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

//    @PostMapping("/api/comments")
//    public void createComment(@RequestBody CommentRequestDto commentRequestDto){
//        commentService.createComment(commentRequestDto);
//    }
//
//    @GetMapping("/api/reply/{postId}")
//    public List<Comment> getComment(@PathVariable Long postId){
//        return CommentService.getComment(postId);
//    }



}
