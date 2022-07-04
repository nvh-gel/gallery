package com.eden.gallery.service;

import com.eden.gallery.viewmodel.ArticleVM;

import java.util.List;

public interface ArticleService {

    /**
     * Create an article.
     *
     * @param request creation request
     */
    void createArticle(ArticleVM request);

    /**
     * Find all articles.
     *
     * @return list of all article
     */
    List<ArticleVM> findAllArticles();

    /**
     * Find article by id.
     *
     * @param id article id to find
     * @return found article
     */
    ArticleVM findArticleById(Long id);

    /**
     * Update an article.
     *
     * @param request update request
     */
    void updateArticle(ArticleVM request);

    /**
     * Delete an article by id.
     *
     * @param id article id to delete
     */
    void deleteArticle(Long id);

    /**
     * Send an article creation request to queue.
     *
     * @param request creation request
     * @return transaction id
     */
    String createArticleOnQueue(ArticleVM request);

    /**
     * Delete an article.
     *
     * @param request article to delete
     */
    void deleteArticle(ArticleVM request);

    /**
     * Send an updating request to queue.
     *
     * @param request update request data
     * @return transaction id
     */
    String updateArticleOnQueue(ArticleVM request);

    /**
     * Send a deleting article to queue.
     *
     * @param id article id to delete
     * @return transaction id
     */
    String deleteArticleOnQueue(Long id);
}
