package com.example.gongguri.repository;

import com.example.gongguri.model.Comment;
import com.example.gongguri.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository  extends JpaRepository<Comment, Long> {


    List<Comment> findAllByPost(Post post);
}
