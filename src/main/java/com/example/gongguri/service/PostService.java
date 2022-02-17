package com.example.gongguri.service;

import com.example.gongguri.dto.PostRequestDto;
import com.example.gongguri.dto.PostResponseDto;
import com.example.gongguri.model.BuyerCount;
import com.example.gongguri.model.Post;
import com.example.gongguri.model.User;
import com.example.gongguri.repository.BuyerCountRepository;
import com.example.gongguri.repository.PostRepository;
import com.example.gongguri.repository.UserRepository;
import com.example.gongguri.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BuyerCountRepository buyerCountRepository;



//    게시글 작성
    @Transactional
    public void createPost(UserDetailsImpl userDetails, PostRequestDto postRequestDto) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);
        Post post = new Post(user,postRequestDto);
        postRepository.save(post);
    }

//   전체 게시글 조회 -
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> allPosts = new ArrayList<>();

            for (Post post : posts) {

                allPosts.add(new PostResponseDto(
                        post.getId(),
                        post.getUser().getUsername(),
                        post.getUser().getNickname(),
                        post.getTitle(),
                        post.getContent(),
                        post.getImageUrl(),
                        post.getStartAt(),
                        post.getEndAt(),
                        post.getPrice(),
                        post.getMinimum(),
                        post.getBuyercount().size()
                ));
            }
            return allPosts;
    }

    public PostResponseDto getPost(Long postId) {

        Post post =postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException( "게시글이 없습니다 ")
        );

        String username = post.getUser().getUsername();
        String nickname = post.getUser().getNickname();
        String title = post.getTitle();
        String content =post.getContent();
        String imageurl = post.getImageUrl();
        String startAt = post.getStartAt();
        String endAt = post.getEndAt();
        int price = post.getPrice();
        int minimum = post.getMinimum();
        int buyercount =post.getBuyercount().size();
        return new PostResponseDto(postId,username,nickname,title,content,imageurl,startAt,endAt,price,minimum,buyercount);
    }

    //게시글 상세페이지 수정
    @Transactional
    public void updatePost(Long postId, UserDetailsImpl userDetails, PostRequestDto postRequestDto) {

        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        post.update(user, postRequestDto);
    }

    public boolean deletePost(Long postId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent() && user.getUserid().equals(post.get().getUser().getUserid()) ) {
            this.postRepository.delete(post.get());
            return true;
        }

//      처리 불가시 주석 처리
//        if(!user.getUserid().equals(post.get().getUser().getUserid())){
//            throw new IllegalArgumentException("해당 게시글을 삭제하실 권한이 없습니다.");
//        }

        postRepository.deleteById(postId);
        return false;
    }

    @Transactional
    public void updateCount(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        User user = userDetails.getUser();

        BuyerCount buyerCount1 = new BuyerCount(user, post, 0);
        BuyerCount buyerCount2 = buyerCountRepository.findByUserAndPost(user,post);


        Optional<BuyerCount> countcheck = buyerCountRepository.findByUserAndPostAndBuycount(user,post,1);

        if(!countcheck.isPresent()){
            buyerCount1.setBuycount(1);
            buyerCountRepository.save(buyerCount1);
        }else {
            buyerCountRepository.delete(buyerCount2);
        }
    }




//  게시글- 구매자 count
//    public void createBuyerCount(Long postId, UserDetailsImpl userDetails) {
//        Post post = postRepository.findById(postId).orElseThrow(
//                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다. ")
//        );
//
//        Optional<BuyerCount> buyerCount = buyerCountRepository.findById(postId);
//
//
//
//        User user = userDetails.getUser();
//        BuyerCount newCount = new BuyerCount(user,post);
//        System.out.println(buyerCount);
//
//        buyerCountRepository.save(newCount);
//    }
}
