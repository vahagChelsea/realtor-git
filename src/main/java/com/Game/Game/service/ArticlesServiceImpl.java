package com.Game.Game.service;

import com.Game.Game.models.Articles;
import com.Game.Game.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    ArticlesRepository repository;

    @Override
    public List<Articles> getAllArticles() {
        return repository.findAll();
    }



    @Override
    public Optional<Articles> getArticlesById(Long id) {
        return Optional.of(repository.findById(id).get());
    }

    @Override
    public List<Articles> getArticlesByBnakavayr(String bnakavayr) {
        return repository.findByBnakavayr(bnakavayr);
    }

    @Override
    public List<Articles> getArticlesByHamaynq(String hamaynq) {
        return repository.findByHamaynq(hamaynq);
    }

    @Override
    public List<Articles> getArticlesByTaracashrjan(String Taracashrjan) {
        return repository.findByTaracashrjan(Taracashrjan);
    }


    @Override
    public boolean delete(Articles articles) {
        try {
            repository.delete(articles);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Articles save(Articles articles) {
        return repository.save(articles);
    }
}
