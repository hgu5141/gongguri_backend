package com.example.gongguri.model;


import com.example.gongguri.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @Column
    private String startAt;

    @Column
    private String endAt;

    @Column
    private int price;

    @Column
    private int minimum;


    @JsonIgnore
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<BuyerCount> buyercount = new ArrayList<>();




    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    @ManyToOne
    private User user;

    public Post(User user,PostRequestDto postRequestDto) {
        this.user = user;
        this.content =postRequestDto.getContent();
        this.imageUrl=postRequestDto.getImageUrl();
        this.title=postRequestDto.getTitle();
        this.startAt=postRequestDto.getStartAt();
        this.endAt=postRequestDto.getEndAt();
        this.price=postRequestDto.getPrice();
        this.minimum=postRequestDto.getMinimum();
    }


    public void update(User user, PostRequestDto postRequestDto) {
        this.user =user;
        this.title =postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }


}
