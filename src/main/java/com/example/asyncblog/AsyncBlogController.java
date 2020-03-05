package com.example.asyncblog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
public class AsyncBlogController {
    private final AsyncBlogService asyncBlogService;

    public AsyncBlogController(AsyncBlogService asyncBlogService) {
        this.asyncBlogService = asyncBlogService;
    }

    @GetMapping("/blog/{id}")
    public Callable<Blog> getBlog(@PathVariable Long id) throws HttpClientErrorException.NotFound {
        return () -> asyncBlogService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Blog Not Found"));
    }

    @PostMapping("/blog")
    public Callable<Blog> addBlog(@RequestBody Blog blog) {
        return () -> asyncBlogService.save(blog);
    }
}
