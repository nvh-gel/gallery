package com.eden.gallery.controller;

import com.eden.gallery.service.ArticleService;
import com.eden.gallery.util.ResponseModel;
import com.eden.gallery.viewmodel.ArticleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    @PostMapping
    private ResponseEntity<ResponseModel> createArticle(@RequestBody ArticleVM request) {

        return ResponseEntity.accepted().body(ResponseModel.created(articleService.createArticleOnQueue(request)));
    }

    @GetMapping
    private ResponseEntity<ResponseModel> findArticle() {

        return ResponseEntity.ok(ResponseModel.ok(articleService.findAllArticles()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseModel> findArticleById(@PathVariable Long id) {

        ArticleVM found = articleService.findArticleById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseModel.notFound());
        } else {
            return ResponseEntity.ok(ResponseModel.ok(found));
        }
    }

    @PutMapping
    private ResponseEntity<ResponseModel> updateArticle(@RequestBody ArticleVM request) {

        return ResponseEntity.accepted().body(ResponseModel.updated(articleService.updateArticleOnQueue(request)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseModel> deleteArticle(@PathVariable Long id) {

        return ResponseEntity.accepted().body(ResponseModel.deleted(articleService.deleteArticleOnQueue(id)));
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
