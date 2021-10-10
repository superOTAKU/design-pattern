package com.example.article.module.article.application.service;

import com.example.article.module.article.application.dto.ArticleReq;

public interface ArticleService {

    Long addArticle(ArticleReq req);

    void modifyArticle(Long id, ArticleReq req);

}
