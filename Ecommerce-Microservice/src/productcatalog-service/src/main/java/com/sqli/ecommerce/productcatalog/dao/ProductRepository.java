package com.sqli.ecommerce.productcatalog.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sqli.ecommerce.productcatalog.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    Optional<Product> findByName(String name);
}
