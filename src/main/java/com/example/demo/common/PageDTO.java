package com.example.demo.common;


import lombok.Data;

import java.util.List;

@Data
public class PageDTO<Content> {
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private List<Content> content;
}
