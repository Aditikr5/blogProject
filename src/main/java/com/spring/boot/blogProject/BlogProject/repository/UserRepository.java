package com.spring.boot.blogProject.BlogProject.repository;

import com.spring.boot.blogProject.BlogProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByuserName(String userName);
}
