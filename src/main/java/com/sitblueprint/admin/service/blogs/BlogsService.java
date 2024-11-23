package com.sitblueprint.admin.service.blogs;

import com.sitblueprint.admin.model.blogs.Blog;

import java.util.List;
public interface BlogsService {
    List<Blog> getAllBlogs();

    Blog getBlogById(Long blogId);

    Blog createBlog(Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlogById(Long blogId);
}
