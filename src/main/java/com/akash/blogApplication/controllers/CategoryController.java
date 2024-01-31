package com.akash.blogApplication.controllers;

import com.akash.blogApplication.payloads.ApiResponse;
import com.akash.blogApplication.payloads.CategoryDto;
import com.akash.blogApplication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId) {
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }


    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryByID(@PathVariable Integer categoryId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    //getAll

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Category deleted successfully",true,HttpStatus.OK),HttpStatus.OK
        );
    }

}
