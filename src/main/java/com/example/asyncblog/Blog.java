package com.example.asyncblog;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private long id;
    private String author;
    private String title;
    private String content;
    private Date date;
}
