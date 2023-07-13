package com.spring.boot.blogProject.BlogProject.repository;

import com.spring.boot.blogProject.BlogProject.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query(value = "SELECT * FROM blogs b where b.user_id=:user_id",nativeQuery = true)
     List<Blog> getBlogByUserIdNativeNamedParam(@Param("user_id") long userId);
}
