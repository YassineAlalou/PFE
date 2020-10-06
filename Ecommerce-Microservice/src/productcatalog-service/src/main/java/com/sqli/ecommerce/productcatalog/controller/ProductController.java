package com.sqli.ecommerce.productcatalog.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.ecommerce.productcatalog.dto.dtos.ProductDto;
import com.sqli.ecommerce.productcatalog.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GetMapping @ApiOperation(value = "Get products list based on page number and page sise.")
    public List<ProductDto> getProducts(
        @RequestParam(defaultValue = "0", name = "page") Integer pageNumber,
        @RequestParam(defaultValue = "10", name = "size") Integer pageSize)
    {
        return productService.getProducts(pageNumber, pageSize);
    }

    @GetMapping("/{id}") @ApiOperation(value = "Get product using the name of the product.")
    public ProductDto getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }
/*
    @GetMapping("/{name}") @ApiOperation(value = "Get product using the name of the product.")
    public ProductDto getProduct(@PathVariable String name){
        return productService.getProduct(name);
    }
*/
    @PostMapping @ApiOperation(value = "Add new product.")
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PutMapping("/{id}") @ApiOperation(value = "Update product using id to find our product.")
    public ProductDto updateProduct (@PathVariable Long id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id,productDto);
    }

    @DeleteMapping("/{id}") @ApiOperation(value = "Delete product using the id.")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
         productService.deleteProduct(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
