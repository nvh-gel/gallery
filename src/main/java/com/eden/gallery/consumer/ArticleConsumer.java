package com.eden.gallery.consumer;

import com.eden.gallery.util.QueueMessage;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.util.Action;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Consumer for article handling.
 */
@Component
@Log4j2
public class ArticleConsumer {

    private ArticleService articleService;

    private final Map<Action, Consumer<ArticleVM>> actionMap;

    /**
     * Constructor.
     */
    public ArticleConsumer() {
        actionMap = new HashMap<>();
        actionMap.put(Action.CREATE, a -> articleService.createArticle(a));
        actionMap.put(Action.UPDATE, a -> articleService.updateArticle(a));
        actionMap.put(Action.DELETE, a -> articleService.deleteArticle(a));
    }

    /**
     * Handle article message from queue.
     *
     * @param message received message
     * @param partitions kafka partitions
     * @param topics kafka topics
     * @param offsets kafka offset
     */
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void processArticleMessage(QueueMessage<ArticleVM> message,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                      @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("Received message <== [{}] on topic {}, partition {}, offset {}",
                message, topics.get(0), partitions.get(0), offsets.get(0));
        actionMap.get(message.getAction()).accept(message.getMessage());
    }

    /**
     * Setter.
     * @param articleService injected article service
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
