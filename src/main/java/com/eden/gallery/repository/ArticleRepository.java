package com.eden.gallery.repository;

import com.eden.gallery.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends SoftDeleteRepository<Article, Long> {
}
