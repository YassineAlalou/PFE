package com.sqli.ecommerce.reviewv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public final class AddReviewDto {

    private Long id;

    private int rating;

    private Long productId;

    private String username;

    private String comment;


}
