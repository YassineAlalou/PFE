package com.sqli.ecommerce.productcatalog.dto.dtos;

import java.io.Serializable;
import java.util.List;

public final class ProductDto implements Serializable {

    private final Long id;

    private final String name;

    private final int price;

    private final String description;

    private final List<String> categories;

    public ProductDto(Long id, String name, int price, String description, List<String> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }
}
