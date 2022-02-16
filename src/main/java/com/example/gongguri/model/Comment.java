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

    @ManyToOne
    private User user;

//    @Column(nullable = false)
//    private Long postId;



    public Comment(Post post,User user, CommentRequestDto commentRequestDto) {
        this.post = post;
        this.user = user;
        this.comment = commentRequestDto.getComment();
    }

}
