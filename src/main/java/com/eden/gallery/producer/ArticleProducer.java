package com.eden.gallery.producer;

import com.eden.common.producer.BaseProducer;
import com.eden.common.utils.QueueMessage;
import com.eden.gallery.viewmodel.ArticleVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer for handling article.
 */
@Component
@Log4j2
public class ArticleProducer implements BaseProducer<ArticleVM> {

    private KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate;

    @Value("${spring.kafka.topic.article}")
    private String topic;

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(QueueMessage<ArticleVM> queueMessage) {

        this.kafkaTemplate.send(topic, queueMessage);
    }

    /**
     * Setter.
     */
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, QueueMessage<ArticleVM>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
