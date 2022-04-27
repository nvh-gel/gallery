package com.eden.gallery.service;

import com.eden.gallery.viewmodel.ArticleVM;

import java.util.List;

public interface ArticleService {
    ArticleVM createArticle(ArticleVM request);

    List<ArticleVM> findAllArticles();

    ArticleVM findArticleById(Long id);

    ArticleVM updateArticle(ArticleVM request);

    ArticleVM deleteArticle(Long id);

}
