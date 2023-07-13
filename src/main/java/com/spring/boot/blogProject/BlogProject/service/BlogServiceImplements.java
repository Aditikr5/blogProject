package com.spring.boot.blogProject.BlogProject.service;

import com.spring.boot.blogProject.BlogProject.entity.Blog;
import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.entity.User;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import com.spring.boot.blogProject.BlogProject.repository.BlogRepository;
import com.spring.boot.blogProject.BlogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogServiceImplements implements BlogService{

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<SuccessMessage> createBlog(long userId, Blog blog) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("UserId is not valid");
        }
        else{
            if(user.get().getSessionToken()!=null){
            Blog blogData=new Blog();
            blogData.setUser(user.get());
            blogData.setTitle(blog.getTitle());
            blogData.setBody(blog.getBody());
            blogData.setPublished(false);
            blogRepository.save(blogData);
                return ResponseEntity.ok(new SuccessMessage(HttpStatus.OK,"Blog Created, You can publish it now"));
            }else {
                throw new UserNotFoundException("User session inactive, Login first to create blog");
            }

        }


    }

    @Override
    public List<Blog> getAllBlogs(long userId) throws UserNotFoundException {
        validateUser(userId);
        return blogRepository.getBlogByUserIdNativeNamedParam(userId);

    }

    @Override
    public boolean validateUser(long userId) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("Invalid userId");
        }
        else{
            if(user.get().getSessionToken()!=null){
                return true;
            }else {
                throw new UserNotFoundException("User session inactive, Login first to perform action");
            }}
    }

    @Override
    public Blog updateBlog(long userId,long blogId,Blog blog) throws UserNotFoundException {
        validateUser(userId);
        Blog blogData = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blogId"));
            if(blogData.getUser().getUserId()==(userId)){
                if(Objects.nonNull(blog.getTitle()) &&
                        !"".equalsIgnoreCase(blog.getTitle())){
                    blogData.setTitle(blog.getTitle());
                }
                if(Objects.nonNull(blog.getBody()) &&
                        !"".equalsIgnoreCase(blog.getBody())){
                    blogData.setBody(blog.getBody());
                }
                return blogRepository.save(blogData);
            }else{
                throw new UserNotFoundException("Unauthorized request,cannot update this blog");
            }
    }

    @Override
    public ResponseEntity<SuccessMessage> deleteBlog(long userId, long blogId) throws UserNotFoundException {
        validateUser(userId);
      Blog blogData= blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blogId"));
        if(blogData.getUser().getUserId()==(userId)){
        blogRepository.deleteById(blogId);
            return ResponseEntity.ok(new SuccessMessage(HttpStatus.OK,"Blog Deleted"));
        }else{
            throw new UserNotFoundException("Unauthorized request,cannot delete this blog");
        }

    }

    @Override
    public Blog publishBlog(long userId, long blogId) throws UserNotFoundException {
        validateUser(userId);
        Blog blogData = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blogId"));
        if(blogData.getUser().getUserId()==(userId)){
            blogData.setPublished(true);
            return blogRepository.save(blogData);
        }else{
            throw new UserNotFoundException("Unauthorized request,cannot update this blog");
        }
    }
}
