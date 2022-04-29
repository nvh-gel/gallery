package com.eden.gallery.producer;

import com.eden.gallery.util.QueueMessage;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer for handling article.
 */
@Component
@Log4j2
public class ArticleProducer {

    private final KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    /**
     * Constructor.
     *
     * @param kafkaTemplate injected kafka template
     */
    public ArticleProducer(KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send message to kafka queue.
     *
     * @param message message to send
     */
    public void send(QueueMessage<ArticleVM> message) {
        this.kafkaTemplate.send(topic, message);
        log.info("Sent message ==> [{}] to topic: {}", message, topic);
    }
}
