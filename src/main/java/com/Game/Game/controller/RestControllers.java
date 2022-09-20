package com.Game.Game.controller;

import com.Game.Game.models.Articles;
import com.Game.Game.service.ArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/home")

public class RestControllers {

    @Autowired
    private ArticlesServiceImpl articlesService;


    @PostMapping("/save")
    public ResponseEntity saveArticles(@RequestBody(required = false)Articles articles){
        articles= articlesService.save(articles);
        return ResponseEntity.ok(articles);
    }

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity list(){
        return  ResponseEntity.ok(articlesService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity articles(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(articlesService.getArticlesById(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticlis(@PathVariable Long id){
        Optional<Articles> articles = articlesService.getArticlesById(id);
        if (articles.isPresent())
            articlesService.delete(articles.get());
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Articles articles ){
        Optional<Articles> articlesOptional=articlesService.getArticlesById(id);
        if(articlesOptional.isPresent()) {
            Articles articles1=articlesOptional.get();
            articles1.setTaracashrjan(articles.getTaracashrjan());
            articles1.setHamaynq(articles.getHamaynq());
            articles1.setBnakavayr(articles.getBnakavayr());

            return ResponseEntity.ok(articlesService.save(articles1));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
