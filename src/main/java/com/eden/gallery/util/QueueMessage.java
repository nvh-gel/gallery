package com.eden.gallery.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Generic message to send and process on queue.
 *
 * @param <T> data type to send/receive
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueMessage<T> {

    private Action action;
    private UUID id;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
    private T message;
}
