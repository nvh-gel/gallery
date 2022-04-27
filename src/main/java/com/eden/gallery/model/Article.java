package com.eden.gallery.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article extends BaseModel {

    private String title;
    private String content;
    private String author;
}
