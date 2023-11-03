package com.example.demo.posts;

import com.example.demo.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class PostEntity extends AbstractEntity {
    private String title;
    private String body;
}
