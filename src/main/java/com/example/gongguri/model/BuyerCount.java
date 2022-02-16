package com.example.gongguri.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class BuyerCount {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long BuyerCountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id")
    private Post post;

    @Column
    private int buycount;

    public BuyerCount(User user, Post post) {
        this.user = user;
        this.post = post;
    }


    public BuyerCount(User user, Post post, int buycount) {
        this.user=user;
        this.post = post;
        this.buycount = buycount;
    }


}
