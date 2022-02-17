package com.example.gongguri.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    
    private String content;
    private String imageUrl;
    private String title;
    private String startAt;
    private String endAt;
    private int price;
    private int minimum;

}
