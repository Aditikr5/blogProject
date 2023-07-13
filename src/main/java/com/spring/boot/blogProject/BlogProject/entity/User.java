package com.spring.boot.blogProject.BlogProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(name = "user_id",sequenceName = "user_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id")
    private long userId;
    private String userName;
    private String password;
    private String sessionToken;
}
