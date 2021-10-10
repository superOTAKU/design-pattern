package com.example.article.module.article.application.service;

import com.example.article.module.article.application.assembler.ArticleAssembler;
import com.example.article.module.article.application.dto.ArticleReq;
import com.example.article.module.article.domain.model.Article;
import com.example.article.module.article.domain.repository.ArticleRepository;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Long addArticle(ArticleReq req) {
        Article article = ArticleAssembler.toArticle(req);
        Date now = new Date();
        article.setCreateTime(now);
        article.setUpdateTime(now);
        repository.save(article);
        return article.getId();
    }

    @Transactional
    @Override
    public void modifyArticle(Long id, ArticleReq req) {
        Session session = entityManager.unwrap(Session.class);
        Article article = session.load(Article.class, id, LockMode.OPTIMISTIC);
        ArticleAssembler.copyTo(req, article);
        session.save(article);
    }

}
