package com.example.gongguri.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultDto {

    private String result;
    List<PostResponseDto> allPosts;

    public ResultDto(String result, List<PostResponseDto> allPosts) {
        this.result=result;
        this.allPosts=allPosts;
    }
}
