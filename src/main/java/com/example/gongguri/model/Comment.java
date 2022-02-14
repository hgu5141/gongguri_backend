package com.example.gongguri.model;


import com.example.gongguri.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

//    @Column(nullable = false)
//    private Long postId;

//    public Comment(CommentRequestDto commentRequestDto) {
//        this.comment=commentRequestDto.getComment();
//    }

    public Comment(Post post, CommentRequestDto commentRequestDto) {
        this.post = post;
        this.comment = commentRequestDto.getComment();
    }

}
