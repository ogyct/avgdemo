package com.example.demo.common;


import lombok.Data;

import java.util.List;

@Data
public class PageObject<C> {
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private List<C> content;
}
