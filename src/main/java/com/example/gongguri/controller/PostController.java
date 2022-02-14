package com.example.gongguri.controller;


import com.sparta.gongguri.dto.PostRequestDto;
import com.sparta.gongguri.dto.PostResponseDto;
import com.sparta.gongguri.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;


//    공동구매 글 작성
    @PostMapping("/api/posts")
    public void createPost(@RequestBody PostRequestDto postRequestDto){
        postService.createPost(postRequestDto);
    }

//    게시글 조회 ( 전체페이지)
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }


//    게시글 조회 (상세페이지)
    @GetMapping("/api/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

//    게시글 수정 ( 상세페이지)
    @PutMapping("/api/posts/{postId}")
    public Long updateArticle (@PathVariable Long postId,@RequestBody PostRequestDto postRequestDto){
        postService.updatePost(postId,postRequestDto);
        return postId;
    }


//    게시글 수정 (상세페이지 )


    //ResponseEntity

    //글작성
    //글수정 /api/posts/{postId}
    //글삭제 /api/posts/{postId}
    //전체 게시글 조회 /api/posts
    //내가 작성한 게시글 조회 /api/posts/{uid}






}
