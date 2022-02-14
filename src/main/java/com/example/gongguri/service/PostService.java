package com.example.gongguri.service;

import com.example.gongguri.dto.PostRequestDto;
import com.example.gongguri.dto.PostResponseDto;
import com.example.gongguri.model.Post;
import com.example.gongguri.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


//    게시글 작성
    @Transactional
    public void createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
    }

//   전체 게시글 조회 -
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> allPosts = new ArrayList<>();
//            allPosts.add(result= true);

            for (Post post : posts) {
                allPosts.add(new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getImageUrl(),
                        post.getStartAt(),
                        post.getEndAt(),
                        post.getPrice(),
                        post.getMinimum(),
                        post.getBuyercount()
                ));
            }

//        String result = "true";
//        ResultDto resultDto = new ResultDto(result,allPosts);
//            return (List<PostResponseDto>) resultDto;

            return allPosts;
    }

//  게시글 상세 페이지 조회
    public PostResponseDto getPost(Long postId) {

        Post post =postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException( " 게시글이 없습니다 ")
        );
//        boolean result = true;
        String title = post.getTitle();
        String content =post.getContent();
        String imageurl = post.getImageUrl();
        String startAt = post.getStartAt();
        String endAt = post.getEndAt();
        int price = post.getPrice();
        int minimum = post.getMinimum();
        int buyercount =post.getBuyercount();
        return new PostResponseDto(postId,title,content,imageurl,startAt,endAt,price,minimum,buyercount);
    }

    //게시글 상세페이지 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        System.out.println(postRequestDto.getContent());
        System.out.println(postRequestDto.getTitle());
        post.update(postRequestDto);

    }




}
