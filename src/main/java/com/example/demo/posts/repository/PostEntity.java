package com.example.demo.posts.repository;

import com.example.demo.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "post")
@Data
@EqualsAndHashCode(callSuper = true)
public class PostEntity extends AbstractEntity {
    private String title;
    private String body;
}
