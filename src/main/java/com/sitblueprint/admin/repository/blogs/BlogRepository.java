package com.sitblueprint.admin.repository.blogs;

import com.sitblueprint.admin.model.blogs.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
