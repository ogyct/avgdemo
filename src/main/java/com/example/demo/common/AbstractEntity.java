package com.example.demo.common;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {
    @Id
    private Long id;
    @Version
    private Long version;
    private String publicId;
}
