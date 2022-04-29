package com.eden.gallery.service.impl;

import com.eden.gallery.mapper.ArticleMapper;
import com.eden.gallery.model.Article;
import com.eden.gallery.producer.ArticleProducer;
import com.eden.gallery.repository.ArticleRepository;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.util.Action;
import com.eden.gallery.util.QueueMessage;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of article service.
 */
@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);
    private ArticleRepository articleRepository;

    private ArticleProducer articleProducer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createArticle(ArticleVM request) {

        Article article = articleMapper.toModel(request);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article created = articleRepository.save(article);
        log.info("Article created: {}", created);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleVM> findAllArticles() {

        List<Article> articles = articleRepository.findAll();
        return articleMapper.toViewModel(articles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleVM findArticleById(Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        return articleMapper.toViewModel(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArticle(ArticleVM request) {

        Article article = articleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            Article toUpdate = articleMapper.toModel(request);
            articleMapper.mapUpdate(article, toUpdate);
            article.setUpdatedAt(LocalDateTime.now());
            Article updated = articleRepository.save(article);
            log.info("Article updated: {}", updated);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteArticle(Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.softDelete(id);
            log.info("Deleted article with id: {}", id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createArticleOnQueue(ArticleVM request) {

        UUID messageId = UUID.randomUUID();
        QueueMessage<ArticleVM> queueMessage = new QueueMessage<>();
        queueMessage.setAction(Action.CREATE);
        queueMessage.setId(messageId);
        queueMessage.setMessage(request);
        articleProducer.send(queueMessage);
        return messageId.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteArticle(ArticleVM a) {

        deleteArticle(a.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateArticleOnQueue(ArticleVM request) {

        UUID messageId = UUID.randomUUID();
        QueueMessage<ArticleVM> queueMessage = new QueueMessage<>();
        queueMessage.setAction(Action.UPDATE);
        queueMessage.setId(messageId);
        queueMessage.setMessage(request);
        articleProducer.send(queueMessage);
        return messageId.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteArticleOnQueue(Long id) {

        UUID messageId = UUID.randomUUID();
        QueueMessage<ArticleVM> queueMessage = new QueueMessage<>();
        queueMessage.setAction(Action.DELETE);
        queueMessage.setId(messageId);
        ArticleVM message = new ArticleVM();
        message.setId(id);
        queueMessage.setMessage(message);
        articleProducer.send(queueMessage);
        return messageId.toString();
    }

    /**
     * Setter.
     *
     * @param articleRepository injected article repository
     */
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Setter.
     *
     * @param articleProducer injected article kafka producer
     */
    @Autowired
    public void setArticleProducer(ArticleProducer articleProducer) {
        this.articleProducer = articleProducer;
    }
}
