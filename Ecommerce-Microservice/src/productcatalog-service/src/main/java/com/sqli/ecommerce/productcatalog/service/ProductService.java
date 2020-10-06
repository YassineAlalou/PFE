package com.sqli.ecommerce.productcatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sqli.ecommerce.productcatalog.exception.DataNotFoundException;
import com.sqli.ecommerce.productcatalog.dao.ProductRepository;
import com.sqli.ecommerce.productcatalog.dto.ProductConverter;
import com.sqli.ecommerce.productcatalog.dto.dtos.ProductDto;
import com.sqli.ecommerce.productcatalog.entity.Product;

@Service
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryService categoryService;

    public ProductDto getProduct(String name){

        return ProductConverter.fromProduct(productRepository.findByName(name)
            .orElseThrow(() ->  new DataNotFoundException("Product not found: "+name)));

    }
    /*
    public List<ProductDto> getProducts(){

        return productRepository.findAll().stream()
            .map(product -> ProductConverter.fromProduct(product))
            .collect(Collectors.toList());
    }
    */
    public ProductDto getProduct(Long id){

        return ProductConverter.fromProduct(productRepository
            .findById(id)
            .orElseThrow(() ->  new DataNotFoundException("Product not found: "+id)));

    }

    public ProductDto addProduct(ProductDto productDto){

        Product product = ProductConverter.toProduct(productDto);

        product.setCategories(categoryService.getCategoriesByNames(productDto.getCategories()));

        productRepository.save(product);

        return ProductConverter.fromProduct(product);

    }

    public ProductDto updateProduct(Long id, ProductDto productDto){

        if(!productRepository.findById(id).isPresent())
            throw new DataNotFoundException("Product that you want update does not exist, id: "+id);


        Product product = ProductConverter.toProduct(productDto);
        product.setId(id);
        product.setCategories(categoryService.getCategoriesByNames(productDto.getCategories()));

        Product result = productRepository.save(product);
        return ProductConverter.fromProduct(result);
    }

    public void deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);

        productRepository.delete(product
            .orElseThrow(() -> new DataNotFoundException("Product that you want to delete does not exist, id: "+id)));
    }

    public List<ProductDto> getProducts(Integer pageNumber, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);

        return productRepository.findAll(paging).stream()
            .map(product -> ProductConverter.fromProduct(product))
            .collect(Collectors.toList());
    }
}
