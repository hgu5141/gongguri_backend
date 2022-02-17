package com.example.gongguri.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private String imageUrl;
    private String startAt;
    private String endAt;
    private int price;
    private int minimum;
    private int buyercount;

}
