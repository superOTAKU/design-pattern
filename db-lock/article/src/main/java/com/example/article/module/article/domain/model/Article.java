package com.example.article.module.article.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    @Version
    private Long version;

}
