package com.akash.blogApplication.services.impl;

import com.akash.blogApplication.entities.Category;
import com.akash.blogApplication.exceptions.ResourceNotFoundException;
import com.akash.blogApplication.payloads.CategoryDto;
import com.akash.blogApplication.repositories.CategoryRepo;
import com.akash.blogApplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category savedCategory = this.categoryRepo.save(categoryDtoToCategory(categoryDto));
        return this.categoryToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryDtoToCategory(getCategoryById(categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);
        return this.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category","Id",categoryId)
                );
        return this.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();

        return categories.stream().map(category ->
                this.categoryToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryDtoToCategory(getCategoryById(categoryId));
        this.categoryRepo.delete(category);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto,Category.class);
    }

    public CategoryDto categoryToCategoryDto(Category category) {
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
