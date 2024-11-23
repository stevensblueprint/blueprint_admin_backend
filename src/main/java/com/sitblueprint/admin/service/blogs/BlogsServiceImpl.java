package com.sitblueprint.admin.service.blogs;

import com.sitblueprint.admin.model.blogs.Blog;
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

    @Override
    public Blog getBlogById(Long blogId){
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NoSuchElementException("Blog with id " + blogId + " was not found")
        );
    }

    @Override
    public Blog createBlog(Blog blog){
        blog.setDateCreated(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog){
        return blogRepository.saveAndFlush(blog);
    }

    @Override
    public void deleteBlogById(Long blogId){
        Optional<Blog> optionalBlogToDelete = blogRepository.findById(blogId);
        if(optionalBlogToDelete.isEmpty()){
            throw new RuntimeException("Blog with id " + blogId + " was not found");
        }
        blogRepository.deleteById(blogId);
    }
}
