package com.sitblueprint.admin.controller.blogs;

import com.sitblueprint.admin.model.blogs.Blog;
import com.sitblueprint.admin.service.blogs.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/blog/")
public class BlogController {
    @Autowired
    BlogsService blogsService;

    @GetMapping("all")
    public List<Blog> getAllBlogs(){
        return blogsService.getAllBlogs();
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<?> getBlog(@PathVariable("blogId") Long blogId) {
        try {
            Blog blog = blogsService.getBlogById(blogId);
            return ResponseEntity.ok(blog);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid blog id format");
        }
    }

    @PostMapping("/create")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogsService.createBlog(blog);
    }

    @PutMapping("/update")
    public Blog updateBlog(@RequestBody Blog blog) {
        return blogsService.updateBlog(blog);
    }

    @DeleteMapping("/{blogId}")
    public void deleteBlog(String blogId) {
        blogsService.deleteBlogById(Long.parseLong(blogId));
    }
}
