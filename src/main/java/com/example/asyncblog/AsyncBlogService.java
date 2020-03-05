package com.example.asyncblog;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AsyncBlogService {

    private Map<Long, Blog> blogs = new ConcurrentHashMap<>();
    private static AtomicInteger currentId = new AtomicInteger(0);

    public Optional<Blog> findById(Long id) {
        return Optional.ofNullable(blogs.get(id));
    }

    public Blog save(Blog blog) {
        int i = currentId.incrementAndGet();
        blog.setId(i);
        blogs.put(blog.getId(), blog);

        return blog;
    }
}
