package com.example.article.module.article.application.assembler;

import com.example.article.module.article.application.dto.ArticleReq;
import com.example.article.module.article.domain.model.Article;
import org.springframework.beans.BeanUtils;

public class ArticleAssembler {

    public static Article toArticle(ArticleReq req) {
        Article article = new Article();
        BeanUtils.copyProperties(req, article);
        return article;
    }

    public static void copyTo(ArticleReq req, Article article) {
        BeanUtils.copyProperties(req, article);
    }

}
