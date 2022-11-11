package com.eden.gallery.controller;

import com.eden.common.utils.ResponseModel;
import com.eden.gallery.service.ArticleService;
import com.eden.gallery.viewmodel.ArticleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for article handling.
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    /**
     * {@inheritDoc}
     */
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody ArticleVM request) {

        return ResponseEntity.accepted()
                .body(ResponseModel.created(articleService.createOnQueue(request)));
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {

        return ResponseEntity.ok(ResponseModel.ok(articleService.findAll()));
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> findById(@PathVariable Long id) {

        ArticleVM articleVM = articleService.findById(id);
        return articleVM == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseModel.notFound())
                : ResponseEntity.ok(ResponseModel.ok(articleVM));
    }

    /**
     * {@inheritDoc}
     */
    @PutMapping
    public ResponseEntity<ResponseModel> update(@RequestBody ArticleVM request) {

        return ResponseEntity.accepted()
                .body(ResponseModel.updated(articleService.updateOnQueue(request)));
    }

    /**
     * {@inheritDoc}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {

        return ResponseEntity.accepted()
                .body(ResponseModel.deleted(articleService.deleteOnQueue(id)));
    }

    /**
     * Setter.
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
