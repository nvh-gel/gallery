package com.eden.gallery.service;

import com.eden.gallery.viewmodel.ArticleVM;

import java.util.List;

public interface ArticleService {
    void createArticle(ArticleVM request);

    List<ArticleVM> findAllArticles();

    ArticleVM findArticleById(Long id);

    void updateArticle(ArticleVM request);

    void deleteArticle(Long id);

    String createArticleOnQueue(ArticleVM request);

    void deleteArticle(ArticleVM a);

    String updateArticleOnQueue(ArticleVM request);

    String deleteArticleOnQueue(Long id);
}
