package com.eden.gallery.consumer;

import com.eden.common.consumer.BaseConsumer;
import com.eden.common.utils.Action;
import com.eden.common.utils.QueueMessage;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

/**
 * Consumer for article handling.
 */
@Component
@Log4j2
public class ArticleConsumer extends BaseConsumer<ArticleVM> {

    private final ArticleService articleService;

    /**
     * Constructor.
     */
    public ArticleConsumer(ArticleService articleService) {

        this.articleService = articleService;

        actionMap.put(Action.CREATE, this.articleService::create);
        actionMap.put(Action.UPDATE, this.articleService::update);
        actionMap.put(Action.DELETE, a -> this.articleService.delete(a.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.article}")
    public void processMessage(QueueMessage<ArticleVM> queueMessage) {

        UnaryOperator<ArticleVM> action = actionMap.getOrDefault(queueMessage.getAction(), null);
        if (action != null) {
            log.info("received message {}", queueMessage);
            action.apply(queueMessage.getMessage());
        }
    }
}
