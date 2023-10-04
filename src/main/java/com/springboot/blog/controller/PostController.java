package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @SecurityRequirement(
            name="Bear Authentication"
    )
    //create blog post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET All Post REST API",
            description = "GET All Post REST API is used to fetch all post from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    //get all post rest api
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(@RequestParam(value ="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "GET Post By Id REST API",
            description = "GET Post By Id REST API is used to get single post from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //get post by id rest api
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
       return ResponseEntity.ok(postService.getPostById(id));

    }

    @Operation(
            summary = "UPDATE Post REST API",
            description = "PUT Post REST API is used to update post in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name="Bear Authentication"
    )
    //update post by id rest api
    @PreAuthorize("hasRole('ADMIN')")
    @ PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "DELETE Post REST API",
            description = "DELETE Post REST API is used to delete post in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name="Bear Authentication"
    )
    //delete post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }


    @Operation(
            summary = "GET Post By Category REST API",
            description = "GET Post By Category REST API is used to fetch posts from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name="Bear Authentication"
    )
    //Build Get Posts by category REST API
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>>getPostsByCategory
    (@PathVariable("id")Long categoryId){
        List<PostDto> postDtos=postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
