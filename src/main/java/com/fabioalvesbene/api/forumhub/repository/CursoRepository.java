package com.fabioalvesbene.api.forumhub.repository;

import com.fabioalvesbene.api.forumhub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//public interface CursoRepository extends JpaRepository<Curso, Long> {
//}
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);
}
