package com.example.asyncblog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
public class AsyncBlogController {
    private final AsyncBlogService asyncBlogService;

    public AsyncBlogController(AsyncBlogService asyncBlogService) {
        this.asyncBlogService = asyncBlogService;
    }

    @GetMapping("/blog/{id}")
    public Blog getBlog(@PathVariable Long id) throws HttpClientErrorException.NotFound {
        return asyncBlogService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Blog Not Found"));
    }

    @PostMapping("/blog")
    public Blog addBlog(@RequestBody Blog blog) {
        return asyncBlogService.save(blog);
    }
}
