package com.sqli.ecommerce.review.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sqli.ecommerce.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

   Long countByRatingAndProductId(int review, Long productIp);

   @Query("SELECT AVG(rating) from Review where product_id=:productId")
   Double findAverageReviews(@Param("productId") Long productId);


}
