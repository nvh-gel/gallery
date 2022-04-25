package com.eden.gallery.controller;

import com.eden.gallery.service.ArticleService;
import com.eden.gallery.util.ResponseModel;
import com.eden.gallery.viewmodel.ArticleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    @PostMapping
    private ResponseEntity<ResponseModel> createArticle(@RequestBody ArticleVM request) {

        return ResponseEntity.accepted().body(ResponseModel.created(articleService.createArticle(request)));
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
