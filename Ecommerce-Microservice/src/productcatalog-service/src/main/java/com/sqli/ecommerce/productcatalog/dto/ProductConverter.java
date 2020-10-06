package com.sqli.ecommerce.productcatalog.dto;

import java.util.stream.Collectors;

import com.sqli.ecommerce.productcatalog.dto.dtos.ProductDto;
import com.sqli.ecommerce.productcatalog.entity.Category;
import com.sqli.ecommerce.productcatalog.entity.Product;

public class ProductConverter {

    private ProductConverter(){}

    public static ProductDto fromProduct(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategories().stream().map(category -> category.getName()).collect(Collectors.toList())
        );
    }

    public static Product toProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                productDto.getCategories().stream().map(categoryName -> new Category(null,categoryName)).collect(Collectors.toList())
        );
    }
}
