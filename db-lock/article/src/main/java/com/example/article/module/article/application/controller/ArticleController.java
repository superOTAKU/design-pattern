package com.example.article.module.article.application.controller;

import com.example.article.module.article.application.dto.ArticleReq;
import com.example.article.module.article.application.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long addArticle(@RequestBody ArticleReq req) {
        return service.addArticle(req);
    }

}
