package com.sqli.ecommerce.review.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sqli.ecommerce.review.dao.ReviewRepository;
import com.sqli.ecommerce.review.dto.ReviewDto;
import com.sqli.ecommerce.review.entity.Review;
import com.sqli.ecommerce.review.exception.RatingNotSupportedException;

@Service
public class ReviewService {

    @Inject
    private ReviewRepository reviewRepository;


    public ReviewDto getReviewsByProductId(Long productId){

        return new ReviewDto(productId,
            reviewRepository.findAverageReviews(productId),
            reviewRepository.countByRatingAndProductId(1,productId),
            reviewRepository.countByRatingAndProductId(2,productId),
            reviewRepository.countByRatingAndProductId(3,productId),
            reviewRepository.countByRatingAndProductId(4,productId),
            reviewRepository.countByRatingAndProductId(5,productId)
            );
    }

    public Review addReview(Review review){
        if(review.getRating()<=0 || review.getRating()>5)
            throw new RatingNotSupportedException("your rating is not supported :"+review.getRating());
        return reviewRepository.save(review);
    }

}
