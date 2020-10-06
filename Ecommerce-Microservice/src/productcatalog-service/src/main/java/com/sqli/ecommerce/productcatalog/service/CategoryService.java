package com.sqli.ecommerce.productcatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sqli.ecommerce.productcatalog.exception.DataNotFoundException;
import com.sqli.ecommerce.productcatalog.dao.CategoryRepository;
import com.sqli.ecommerce.productcatalog.entity.Category;

@Service
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    /*
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    */

    public List<Category> getCategories(Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return categoryRepository.findAll(paging).toList();
    }

    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
            .orElseThrow(() ->  new DataNotFoundException("Category not found: "+id));
    }

    public Category getCategoryByName(String name) {

        return categoryRepository.findByName(name)
            .orElseThrow(() ->  new DataNotFoundException("Category not found: "+name));
    }

    public List<Category> getCategoriesByNames(List<String> names){

        return names.stream().map(name -> getCategoryByName(name)).collect(Collectors.toList());

    }

    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {

        if(!categoryRepository.findById(id).isPresent())
            throw new DataNotFoundException("Category that you want update does not exist, id: "+id);

        category.setId(id);


        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        categoryRepository.delete(category
            .orElseThrow(() -> new DataNotFoundException("Category that you want to delete does not exist, id: "+id)));
    }
}
