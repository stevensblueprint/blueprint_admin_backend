package com.sitblueprint.admin.service.blogs;

import com.sitblueprint.admin.model.blogs.Blog;
import com.sitblueprint.admin.repository.blogs.BlogRepository;
import com.sitblueprint.admin.repository.blogs.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogsServiceImpl implements BlogsService {
    private static final Logger log = LoggerFactory.getLogger(BlogsServiceImpl.class);
    private final BlogRepository blogRepository;

    public BlogsServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
