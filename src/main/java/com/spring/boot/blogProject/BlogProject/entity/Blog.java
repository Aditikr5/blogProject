package com.spring.boot.blogProject.BlogProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="blogs")
public class Blog {
    @Id
    @SequenceGenerator(name = "blog_id",sequenceName = "blog_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blog_id")
    private long blogId;
    private String title;
    private String body;
    private boolean published;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
}
