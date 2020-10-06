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

import com.sqli.ecommerce.productcatalog.entity.Category;
import com.sqli.ecommerce.productcatalog.service.CategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Inject
    private CategoryService categoryService;

    @GetMapping @ApiOperation(value = "Get all categories based on page number and page size.")
    public List<Category> getCategories(
        @RequestParam(defaultValue = "0", name = "page") Integer pageNumber,
        @RequestParam(defaultValue = "10", name = "size") Integer pageSize)
    {
        return categoryService.getCategories(pageNumber, pageSize);
    }

    @GetMapping("/{id}") @ApiOperation(value = "Get category using the id of the category.")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
/*
    @GetMapping(params = {"name"}) @ApiOperation(value = "Get category using the name of the category.")
    public Category getCategoryByName(@RequestParam(value="name") String name){
        return categoryService.getCategoryByName(name);
    }
*/
    @PostMapping @ApiOperation(value = "Add new category.")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}") @ApiOperation(value = "Update category using id to find our category.")
    public Category updateCategory (@PathVariable Long id, @RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/{id}") @ApiOperation(value = "Delete category using the id.")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
