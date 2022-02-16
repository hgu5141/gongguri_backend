package com.example.gongguri.repository;

import com.example.gongguri.model.BuyerCount;
import com.example.gongguri.model.Post;
import com.example.gongguri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerCountRepository extends JpaRepository<BuyerCount,Long> {

    BuyerCount findByUserAndPost(User user, Post post);

    Optional<BuyerCount> findByUserAndPostAndBuycount(User user, Post post,int buycount);
//    BuyerCount count(User user, Post post, BuyerCount buyerCount);
//
//    BuyerCount findByUserAndPostId(User user, Long postId, int count);
//
//    void deleteByUserAndPostId(int count);
}
