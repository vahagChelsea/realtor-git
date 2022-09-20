package com.Game.Game.repositories;


import com.Game.Game.models.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {

       Optional<Articles> findById(Long id);

       List<Articles> findByBnakavayr(String bnakavayr);

       List<Articles> findByHamaynq(String hamaynq);

       List<Articles> findByTaracashrjan(String taracashrjan);

}
