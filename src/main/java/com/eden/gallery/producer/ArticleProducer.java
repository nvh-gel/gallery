package com.eden.gallery.producer;

import com.eden.gallery.message.QueueMessage;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ArticleProducer {

    private final KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public ArticleProducer(KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(QueueMessage<ArticleVM> message) {
        this.kafkaTemplate.send(topic, message);
        log.info("Sent message ==> [{}] to topic: {}", message, topic);
    }
}
