package com.eden.gallery.message;

import com.eden.gallery.util.Action;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueMessage<T> {

    private Action action;
    private UUID id;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
    private T message;
}
