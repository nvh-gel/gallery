package com.eden.gallery.viewmodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * View model for article.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVM extends BaseVM {

    private String title;
    private String content;
    private String author;
}
