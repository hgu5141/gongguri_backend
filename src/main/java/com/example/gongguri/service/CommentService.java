package com.example.gongguri.service;


import com.example.gongguri.dto.CommentRequestDto;
import com.example.gongguri.dto.CommentResponseDto;
import com.example.gongguri.model.Comment;
import com.example.gongguri.model.Post;
import com.example.gongguri.model.User;
import com.example.gongguri.repository.CommentRepository;
import com.example.gongguri.repository.PostRepository;
import com.example.gongguri.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //코멘트 저장
    public void save(Long postId, UserDetailsImpl userDetails, CommentRequestDto commentRequestDto){

        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(post,user, commentRequestDto);
        System.out.println(comment);

        commentRepository.save(comment);
    }
    //코멘트 조회
    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        List<Comment> commentList = commentRepository.findAllByPost(post);
        List<CommentResponseDto> comments =new ArrayList<>();
        for (Comment comment1 : commentList){
            Long commentId = comment1.getCommentId();
            String comment = comment1.getComment();

            CommentResponseDto commentResponseDto = new CommentResponseDto(commentId,comment);
            comments.add(commentResponseDto);
        }
            return comments;
    }

    public void deleteComments(Long commentId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("삭제할 코멘트가 존재하지 않습니다.")
        );

        commentRepository.delete(comment);
    }


//    public Comment createComment(CommentRequestDto commentRequestDto) {
//        Comment comment = new Comment(commentRequestDto);
//        commentRepository.save(comment);
//        return comment;
//    }
//
//    public List<Comment> getComment(Long postId)   {
//        return CommentRepository.findAllByPostid(postId);
//    }

}
