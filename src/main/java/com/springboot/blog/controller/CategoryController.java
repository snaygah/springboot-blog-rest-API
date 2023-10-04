package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to save category into database"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    //Build Add Category REST API
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET Category By Id REST API",
            description = "GET Category By Id REST API is used to get single category from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //Build Get Category REST API
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
        CategoryDto categoryDto=categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);

    }

    @Operation(
            summary = "GET All Categories REST API",
            description = "GET All Categories REST API is used to fetch all categories from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //Build Get All Categories REST API
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @Operation(
            summary = "UPDATE Category REST API",
            description = "PUT Category REST API is used to update category in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //Build Update Category REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id")Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto,categoryId));
    }

    @Operation(
            summary = "DELETE Category REST API",
            description = "DELETE Category REST API is used to delete category in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //Build Delete Category REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok("Category deleted successfully");
    }
}
