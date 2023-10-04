package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto>getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
    void deleteCategory(Long categoryId);
}
