package com.Game.Game.service;

import com.Game.Game.models.Articles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArticlesService {

    List<Articles> getAllArticles();

    Optional<Articles> getArticlesById(Long id);

    List<Articles> getArticlesByBnakavayr(String bnakavayr);

    List<Articles> getArticlesByHamaynq(String hamaynq);

    List<Articles> getArticlesByTaracashrjan(String Taracashrjan);

    boolean delete (Articles articles);

    Articles save(Articles articles);



}
