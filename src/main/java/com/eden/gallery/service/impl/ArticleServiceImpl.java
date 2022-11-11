package com.eden.gallery.service.impl;

import com.eden.common.utils.Action;
import com.eden.gallery.mapper.ArticleMapper;
import com.eden.gallery.model.Article;
import com.eden.gallery.producer.ArticleProducer;
import com.eden.gallery.repository.ArticleRepository;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of article service.
 */
@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    private ArticleProducer articleProducer;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM create(ArticleVM articleVM) {

        Article article = articleMapper.toModel(articleVM);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article created = articleRepository.save(article);
        return articleMapper.toViewModel(created);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createOnQueue(ArticleVM articleVM) {

        return articleProducer.sendProcessingMessageToQueue(Action.CREATE, articleVM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleVM> findAll() {

        List<Article> articles = articleRepository.findAll();
        return articleMapper.toViewModel(articles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleVM findById(Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        return articleMapper.toViewModel(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM update(ArticleVM articleVM) {

        Article exist = articleRepository.findById(articleVM.getId()).orElse(null);
        if (exist == null) {
            return null;
        }
        Article toUpdate = articleMapper.toModel(articleVM);
        articleMapper.mapUpdate(exist, toUpdate);
        exist.setUpdatedAt(LocalDateTime.now());
        Article updated = articleRepository.save(exist);
        return articleMapper.toViewModel(updated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateOnQueue(ArticleVM articleVM) {

        return articleProducer.sendProcessingMessageToQueue(Action.UPDATE, articleVM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM delete(Long id) {

        Article exist = articleRepository.findById(id).orElse(null);
        if (exist == null) {
            return null;
        }
        articleRepository.deleteById(id);
        exist.setUpdatedAt(LocalDateTime.now());
        return articleMapper.toViewModel(exist);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteOnQueue(Long id) {

        ArticleVM articleVM = new ArticleVM();
        articleVM.setId(id);
        return articleProducer.sendProcessingMessageToQueue(Action.DELETE, articleVM);
    }

    /**
     * Setter.
     */
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Setter.
     */
    @Autowired
    public void setArticleProducer(ArticleProducer articleProducer) {
        this.articleProducer = articleProducer;
    }
}
