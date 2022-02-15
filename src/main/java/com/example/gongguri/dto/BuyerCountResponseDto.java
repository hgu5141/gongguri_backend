package com.example.gongguri.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuyerCountResponseDto {

    private Long postId;
    private String username;
    private String nickname;
    private int buycount;
}
