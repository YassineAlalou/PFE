package com.sqli.ecommerce.reviewv2.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public final class ReviewDto implements Serializable {

    private final Long product_id;

    private final Double avg_ratings;

    private final Long total_rating_1;

    private final Long total_rating_2;

    private final Long total_rating_3;

    private final Long total_rating_4;

    private final Long total_rating_5;

    private final List<CommentDto> comments;


}
