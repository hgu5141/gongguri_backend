package com.example.gongguri.controller;


import com.example.gongguri.dto.PostRequestDto;
import com.example.gongguri.dto.PostResponseDto;
import com.example.gongguri.exception.RestApiException;

import com.example.gongguri.security.UserDetailsImpl;
import com.example.gongguri.service.PostService;
import com.example.gongguri.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final S3Uploader s3Uploader;



    //    공동구매 글 작성
    @PostMapping("/api/posts")
    public void createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        postService.createPost(userDetails, postRequestDto);
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
    public Long updateArticle(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(postId, userDetails, postRequestDto);
        return postId;
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        postService.deletePost(postId, userDetails);

        try {
            boolean isDelete = this.postService.deletePost(postId, userDetails);
            if (isDelete) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.badRequest().body("can't find entity");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid Parameter");
        }
    }

//    이미지 업로드
    @PostMapping("/api/image")
    public ResponseEntity<String> updateUserImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        String image = s3Uploader.uploadFile(multipartFile, "static");
        return ResponseEntity.ok()
                .body(image);
    }


    @PostMapping("/api/posts/{postId}/buycount")
    public void updateCount(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updateCount(postId, userDetails);
    }
}

