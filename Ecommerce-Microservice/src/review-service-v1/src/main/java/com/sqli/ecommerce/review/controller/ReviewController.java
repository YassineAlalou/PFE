package com.sqli.ecommerce.review.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.ecommerce.review.dto.ReviewDto;
import com.sqli.ecommerce.review.entity.Review;
import com.sqli.ecommerce.review.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Inject
    ReviewService reviewService;


    @GetMapping("/{product-id}") @ApiOperation(value = "Get all reviews of a product using the id of the product.")
    public ReviewDto getReviewByProductId(@PathVariable(name = "product-id") Long productId){
        return reviewService.getReviewsByProductId(productId);
    }

    @PostMapping @ApiOperation(value = "Add new review.")
    public Review addReview(@RequestBody Review review){

        return reviewService.addReview(review);
    }

}
