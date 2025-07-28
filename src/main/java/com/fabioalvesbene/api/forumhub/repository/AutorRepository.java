package com.fabioalvesbene.api.forumhub.repository;

import com.fabioalvesbene.api.forumhub.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//public interface AutorRepository extends JpaRepository<Autor, Long> {
//}

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);
}