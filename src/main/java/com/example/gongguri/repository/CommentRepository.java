package com.example.gongguri.repository;

import com.example.gongguri.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository  extends JpaRepository<Comment, Long> {


    List<Comment> findAllByPost(Long postId);


//    List<Comment> findAllByPostId(Long postId);
}
