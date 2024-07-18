package com.example.demo.common;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    private String publicId = UUID.randomUUID().toString();
}
