package com.sqli.ecommerce.reviewv2.dto;

import com.sqli.ecommerce.reviewv2.entity.Comment;
import com.sqli.ecommerce.reviewv2.entity.Review;

public class DtoConverter {

    private  DtoConverter(){}

     public static CommentDto toCommentDto(Comment comment){
        return new CommentDto(comment.getUsername(),
            comment.getComment());
     }

    public static Review toReview(AddReviewDto addReviewDto){
        return new Review(null,
            addReviewDto.getRating(),
            addReviewDto.getProductId());
    }

}
