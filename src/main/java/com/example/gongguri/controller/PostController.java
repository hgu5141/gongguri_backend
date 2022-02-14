package com.example.gongguri.controller;

import com.example.gongguri.dto.PostRequestDto;
import com.example.gongguri.dto.PostResponseDto;
import com.example.gongguri.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;


    //    공동구매 글 작성
    @PostMapping("/api/posts")
    public void createPost(@RequestBody PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);
    }

    //    게시글 조회 ( 전체페이지)
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }


    //    게시글 조회 (상세페이지)
    @GetMapping("/api/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    //    게시글 수정 ( 상세페이지)
    @PutMapping("/api/posts/{postId}")
    public Long updateArticle(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return postId;
    }
}
