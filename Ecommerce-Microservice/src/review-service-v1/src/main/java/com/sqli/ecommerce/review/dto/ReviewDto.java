package com.sqli.ecommerce.review.dto;

import java.io.Serializable;
import java.util.List;

public final class ReviewDto implements Serializable {

    private final Long product_id;

    private final Double avg_ratings;

    private final Long total_rating_1;

    private final Long total_rating_2;

    private final Long total_rating_3;

    private final Long total_rating_4;

    private final Long total_rating_5;

    public ReviewDto(Long product_id, double avg_ratings, Long total_rating_1, Long total_rating_2, Long total_rating_3, Long total_rating_4, Long total_rating_5) {
        this.product_id = product_id;
        this.avg_ratings = avg_ratings;
        this.total_rating_1 = total_rating_1;
        this.total_rating_2 = total_rating_2;
        this.total_rating_3 = total_rating_3;
        this.total_rating_4 = total_rating_4;
        this.total_rating_5 = total_rating_5;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public double getAvg_ratings() {
        return avg_ratings;
    }

    public Long getTotal_rating_1() {
        return total_rating_1;
    }

    public Long getTotal_rating_2() {
        return total_rating_2;
    }

    public Long getTotal_rating_3() {
        return total_rating_3;
    }

    public Long getTotal_rating_4() {
        return total_rating_4;
    }

    public Long getTotal_rating_5() {
        return total_rating_5;
    }
}
