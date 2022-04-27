package com.eden.gallery.service.impl;

import com.eden.gallery.mapper.ArticleMapper;
import com.eden.gallery.model.Article;
import com.eden.gallery.repository.ArticleRepository;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.viewmodel.ArticleVM;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);
    private ArticleRepository articleRepository;

    @Override
    public ArticleVM createArticle(ArticleVM request) {

        Article article = articleMapper.toModel(request);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article created = articleRepository.save(article);
        return articleMapper.toViewModel(created);
    }

    @Override
    public List<ArticleVM> findAllArticles() {

        List<Article> articles = articleRepository.findAll();
        return articleMapper.toViewModel(articles);
    }

    @Override
    public ArticleVM findArticleById(Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        return articleMapper.toViewModel(article);
    }

    @Override
    public ArticleVM updateArticle(ArticleVM request) {

        Article article = articleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            Article toUpdate = articleMapper.toModel(request);
            articleMapper.mapUpdate(article, toUpdate);
            article.setUpdatedAt(LocalDateTime.now());
            Article updated = articleRepository.save(article);
            return articleMapper.toViewModel(updated);
        }
        return null;
    }

    @Override
    public ArticleVM deleteArticle(Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.softDelete(id);
            return articleMapper.toViewModel(article);
        }
        return null;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
