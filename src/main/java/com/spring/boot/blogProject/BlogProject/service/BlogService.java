package com.spring.boot.blogProject.BlogProject.service;

import com.spring.boot.blogProject.BlogProject.entity.Blog;
import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    public ResponseEntity<SuccessMessage> createBlog(long userId, Blog blog) throws UserNotFoundException;

    public List<Blog> getAllBlogs(long userId) throws UserNotFoundException;
    public boolean validateUser(long userId) throws UserNotFoundException;
    public Blog updateBlog(long userId,long blogId,Blog blog) throws UserNotFoundException;
    public ResponseEntity<SuccessMessage> deleteBlog(long userId,long blogId) throws UserNotFoundException;
    public Blog publishBlog(long userId,long blogId) throws UserNotFoundException;
}
