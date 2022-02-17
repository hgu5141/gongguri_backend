package com.example.gongguri.repository;


import com.example.gongguri.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post,Long> {

}