package com.spring.boot.blogProject.BlogProject.controller;

import com.spring.boot.blogProject.BlogProject.entity.Blog;
import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.entity.User;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import com.spring.boot.blogProject.BlogProject.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

@Autowired
    private BlogService blogService;


    @PostMapping("/createBlog")
    public ResponseEntity<SuccessMessage> createBlog(@RequestParam long userId, @RequestBody Blog blog) throws UserNotFoundException {
        return blogService.createBlog(userId,blog);
    }

    @GetMapping("/allBlogs")
    public List<Blog> getAllBlogsList(@RequestParam long userId) throws UserNotFoundException {
        return blogService.getAllBlogs(userId);
    }

    @PutMapping("/updateBlog")
    public Blog updateBlogById(@RequestParam long userId,@RequestParam long blogId,@RequestBody Blog blog)throws UserNotFoundException{
        return blogService.updateBlog(userId,blogId,blog);
    }

    @DeleteMapping("deleteBlog")
    public ResponseEntity<SuccessMessage> deleteBlog(@RequestParam long userId,@RequestParam long blogId)throws UserNotFoundException{
        return blogService.deleteBlog(userId,blogId);
    }

    @PutMapping("/publishBlog")
    public Blog publishBlog(@RequestParam long userId,@RequestParam long blogId) throws UserNotFoundException{
        return blogService.publishBlog(userId,blogId);
    }
}
