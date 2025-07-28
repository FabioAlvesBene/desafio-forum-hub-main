package com.fabioalvesbene.api.forumhub.repository;

import com.fabioalvesbene.api.forumhub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
