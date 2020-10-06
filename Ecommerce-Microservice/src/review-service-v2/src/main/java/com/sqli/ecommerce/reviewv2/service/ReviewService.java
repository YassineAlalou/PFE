package com.sqli.ecommerce.reviewv2.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sqli.ecommerce.reviewv2.dao.CommentRepository;
import com.sqli.ecommerce.reviewv2.dao.ReviewRepository;
import com.sqli.ecommerce.reviewv2.dto.AddReviewDto;
import com.sqli.ecommerce.reviewv2.dto.CommentDto;
import com.sqli.ecommerce.reviewv2.dto.DtoConverter;
import com.sqli.ecommerce.reviewv2.dto.ReviewDto;
import com.sqli.ecommerce.reviewv2.entity.Comment;
import com.sqli.ecommerce.reviewv2.entity.Review;
import com.sqli.ecommerce.reviewv2.exception.RatingNotSupportedException;

@Service
@Transactional
public class ReviewService {

    @Inject
    private ReviewRepository reviewRepository;

    @Inject
    private CommentRepository commentRepository;


    public ReviewDto getReviewsByProductId(Long productId){



        return new ReviewDto(productId,
            reviewRepository.findAverageReviews(productId),
            reviewRepository.countByRatingAndProductId(1,productId),
            reviewRepository.countByRatingAndProductId(2,productId),
            reviewRepository.countByRatingAndProductId(3,productId),
            reviewRepository.countByRatingAndProductId(4,productId),
            reviewRepository.countByRatingAndProductId(5,productId),
            getCommentDtos(getReviewsIds(productId))
            );
    }

    public AddReviewDto addReview(AddReviewDto addReviewDto){
        if(addReviewDto.getRating()<=0 || addReviewDto.getRating()>5)
            throw new RatingNotSupportedException("your rating is not supported :"+addReviewDto.getRating());

        Review review = reviewRepository.save(DtoConverter.toReview(addReviewDto));

        Comment comment = new Comment();

        if(addReviewDto.getComment() != null) {
            comment = commentRepository.save(new Comment(review.getId(),
                addReviewDto.getUsername(),
                addReviewDto.getComment()));
        }

        return new AddReviewDto(review.getId(),
            review.getRating(),
            review.getProductId(),
            comment.getUsername(),
            comment.getComment());

    }

    private List<CommentDto> getCommentDtos(List<Long> reviewsIds) {
        return commentRepository.findAllById(reviewsIds).stream()
            .map(DtoConverter::toCommentDto)
            .collect(Collectors.toList());
    }

    private List<Long> getReviewsIds(Long productId) {
        return  reviewRepository.findAllByProductId(productId).stream()
            .map(Review::getId)
            .collect(Collectors.toList());
    }


}
