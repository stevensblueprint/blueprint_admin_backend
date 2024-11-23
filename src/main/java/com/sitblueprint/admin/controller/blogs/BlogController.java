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
}
