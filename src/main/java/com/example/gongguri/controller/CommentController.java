package com.example.gongguri.controller;



import com.example.gongguri.dto.CommentRequestDto;
import com.example.gongguri.dto.CommentResponseDto;
import com.example.gongguri.security.UserDetailsImpl;
import com.example.gongguri.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


//    코멘트 작성 - 로그인 되어 있으면 작성가능, 아니면 안됨. void ->
    @PostMapping("/api/{postId}/comments")
    public void createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(postId);
        commentService.save(postId,userDetails, commentRequestDto);
    }

//  코멘트 조회
    @GetMapping("/api/{postId}/comments")
    public List<CommentResponseDto> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

//    코멘트 삭제하기
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//       commentService.deleteComments(commentId,userDetails);

        try {
            boolean isDelete = this.commentService.deleteComments(commentId,userDetails);
            if (isDelete) {
                return ResponseEntity.ok("success");
            }else {
                return ResponseEntity.badRequest().body("코멘트를 삭제하였습니다.");
            }
        }catch(Exception ex) {
            return ResponseEntity.badRequest().body("존재하지 않는 코멘트입니다.");
        }
    }



}
