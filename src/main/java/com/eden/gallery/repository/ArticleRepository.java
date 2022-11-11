package com.eden.gallery.repository;

import com.eden.gallery.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Article JPA repository.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
