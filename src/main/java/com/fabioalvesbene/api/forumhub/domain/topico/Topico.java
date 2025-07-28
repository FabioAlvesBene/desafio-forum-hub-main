package com.fabioalvesbene.api.forumhub.domain.topico;

import com.fabioalvesbene.api.forumhub.domain.autor.Autor;
import com.fabioalvesbene.api.forumhub.domain.curso.Curso;
import com.fabioalvesbene.api.forumhub.domain.resposta.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity(name = "Topico")
//@Table(name = "topicos")
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Topico {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String titulo;
//    private String mensagem;
//    @Column(name = "data_criacao", nullable = false)
//    private LocalDateTime dataCriacao;
//
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "autor_id", nullable = false)
//    private Autor autor;
//
//    @ManyToOne
//    @JoinColumn(name = "curso_id", nullable = false)
//    private Curso curso;
//
//    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Resposta> respostas  = new ArrayList<>();
//
////    public Topico(DadosCadastroTopico dados) {
////        this.titulo = dados.titulo();
////        this.mensagem = dados.mensagem();
////        this.dataCriacao = dados.dataCriacao();
////        this.status = dados.status();
////        this.autor = dados.autor();
////        this.curso = dados.curso();
////        this.respostas = new ArrayList<>();
////
////    }
//
//    public Topico(String titulo, String mensagem, String status, Autor autor, Curso curso) {
//        this.titulo = titulo;
//        this.mensagem = mensagem;
//        this.status = status;
//        this.autor = autor;
//        this.curso = curso;
//        this.dataCriacao = LocalDateTime.now();
//        this.respostas = new ArrayList<>();
//    }
//
//    public void atualizarDados(DadosCadastroTopico dados) {
//        if (dados.titulo() != null) {
//            this.titulo = dados.titulo();
//        }
//        if (dados.mensagem() != null) {
//            this.mensagem = dados.mensagem();
//        }
//        if (dados.dataCriacao() != null) {
//            this.dataCriacao = dados.dataCriacao();
//        }
//        if (dados.status() != null) {
//            this.status = dados.status();
//        }
//        if (dados.autor() != null) {
//            this.autor = dados.autor();
//        }
//        if (dados.curso() != null) {
//            this.curso = dados.curso();
//        }
//    }
//}

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    private String status;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas  = new ArrayList<>();

    // setters para autor e curso
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Topico(DadosCadastroTopico dados, Autor autor, Curso curso) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = dados.dataCriacao() != null ? dados.dataCriacao() : LocalDateTime.now();
        this.status = dados.status();
        this.autor = autor;
        this.curso = curso;
        this.respostas = new ArrayList<>();
    }

    public void atualizarDados(DadosCadastroTopico dados, Autor autor, Curso curso) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.dataCriacao() != null) {
            this.dataCriacao = dados.dataCriacao();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (curso != null) {
            this.curso = curso;
        }
    }
}