package com.sqli.ecommerce.reviewv2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

}
