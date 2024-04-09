package com.sitblueprint.admin.service.blog;

import com.sitblueprint.admin.model.blog.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();

    Blog getBlogById(Long id);

    Blog createBlog(Long blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);

    void setBlogToPublished(Long id);
}
