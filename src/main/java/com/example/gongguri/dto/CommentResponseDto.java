package com.example.gongguri.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDto {

    private String username;
    private String nickname;
    private Long commentId;
    private String comment;


}
