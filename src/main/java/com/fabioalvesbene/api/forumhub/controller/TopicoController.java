//package com.fabioalvesbene.api.forumhub.controller;
//
//import com.fabioalvesbene.api.forumhub.domain.autor.Autor;
//import com.fabioalvesbene.api.forumhub.domain.curso.Curso;
//import com.fabioalvesbene.api.forumhub.domain.topico.DadosCadastroTopico;
//import com.fabioalvesbene.api.forumhub.domain.topico.DadosDetalhamentoTopico;
//import com.fabioalvesbene.api.forumhub.domain.topico.Topico;
//import com.fabioalvesbene.api.forumhub.repository.AutorRepository;
//import com.fabioalvesbene.api.forumhub.repository.CursoRepository;
//import com.fabioalvesbene.api.forumhub.repository.TopicoRepository;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@RestController
//@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
//public class TopicoController {
//
//    @Autowired
//    private TopicoRepository topicoRepository;
//
////    @PostMapping
////    @Transactional
////    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder){
////        var topico = new Topico(dados);
////        topicoRepository.save(topico);
////
////        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
////        return ResponseEntity.created(uri).body((new DadosDetalhamentoTopico(topico)));
////    }
//
//    @Autowired
//    private AutorRepository autorRepository;
//
//    @Autowired
//    private CursoRepository cursoRepository;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder) {
//        // Buscar ou criar Autor
//        Autor autor = autorRepository.findByEmail(dados.autor().email())
//                .orElseGet(() -> autorRepository.save(new Autor(dados.autor().nome(), dados.autor().email())));
//
//        // Buscar ou criar Curso
//        Curso curso = cursoRepository.findByNome(dados.curso().nome())
//                .orElseGet(() -> cursoRepository.save(new Curso(null, dados.curso().nome(), "Categoria padrão")));
//
//        // Criar Topico com entidades resolvidas
//        Topico topico = new Topico(
//                null,
//                dados.titulo(),
//                dados.mensagem(),
//                dados.dataCriacao() != null ? dados.dataCriacao() : LocalDateTime.now(),
//                dados.status(),
//                autor,
//                curso,
//                new ArrayList<>()
//        );
//
//        topicoRepository.save(topico);
//
//        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity buscarTopico(@PathVariable Long id){
//        var topico = topicoRepository.getReferenceById(id);
//        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
//
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<DadosDetalhamentoTopico>> listarTopicos(@PageableDefault(sort = {"titulo"}) Pageable pageable){
//        var pagina = topicoRepository.findAll(pageable).map(DadosDetalhamentoTopico::new);
//        return ResponseEntity.ok(pagina);
//    }
//
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity atualizarTopico(
//            @PathVariable Long id,
//            @RequestBody @Valid DadosCadastroTopico dados) {
//
//        var topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
//
//        topico.atualizarDados(dados);
//        topicoRepository.save(topico);
//
//        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
//        if (!topicoRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        topicoRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}

package com.fabioalvesbene.api.forumhub.controller;

import com.fabioalvesbene.api.forumhub.domain.autor.Autor;
import com.fabioalvesbene.api.forumhub.domain.curso.Curso;
import com.fabioalvesbene.api.forumhub.domain.topico.DadosCadastroTopico;
import com.fabioalvesbene.api.forumhub.domain.topico.DadosDetalhamentoTopico;
import com.fabioalvesbene.api.forumhub.domain.topico.Topico;
import com.fabioalvesbene.api.forumhub.repository.AutorRepository;
import com.fabioalvesbene.api.forumhub.repository.CursoRepository;
import com.fabioalvesbene.api.forumhub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder) {
//        // Busca o Autor pelo email, ou cria e salva se não existir
//        Autor autor = autorRepository.findByEmail(dados.autor().email())
//                .orElseGet(() -> autorRepository.save(new Autor(null, dados.autor().nome(), dados.autor().email())));
//
//        // Busca o Curso pelo nome, ou cria e salva se não existir
//        Curso curso = cursoRepository.findByNome(dados.curso().nome())
//                .orElseGet(() -> cursoRepository.save(new Curso(null, dados.curso().nome(), "Categoria padrão")));
//
//        // Agora cria o Topico usando as entidades já persistidas
//        Topico topico = new Topico(dados, autor, curso);
//
//        // Salva o tópico (referências autor e curso já persistidas)
//        topicoRepository.save(topico);
//
//        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
//    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder) {
        Optional<Autor> autorOpt = autorRepository.findByEmail(dados.autor().email());
        Autor autor = autorOpt.orElseGet(() -> {
            Autor novoAutor = new Autor(null, dados.autor().nome(), dados.autor().email());
            System.out.println("Salvando novo autor: " + novoAutor.getNome());
            return autorRepository.save(novoAutor);
        });

        Optional<Curso> cursoOpt = cursoRepository.findByNome(dados.curso().nome());
        Curso curso = cursoOpt.orElseGet(() -> {
            Curso novoCurso = new Curso(null, dados.curso().nome(), "Categoria padrão");
            System.out.println("Salvando novo curso: " + novoCurso.getNome());
            return cursoRepository.save(novoCurso);
        });

        System.out.println("Autor ID: " + autor.getId());
        System.out.println("Curso ID: " + curso.getId());

        Topico topico = new Topico(dados, autor, curso);
        topicoRepository.save(topico);

        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listarTopicos(@PageableDefault(sort = {"titulo"}) Pageable pageable) {
        var pagina = topicoRepository.findAll(pageable).map(DadosDetalhamentoTopico::new);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopico dados) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        Autor autor = autorRepository.findByEmail(dados.autor().email())
                .orElseGet(() -> autorRepository.save(new Autor(null, dados.autor().nome(), dados.autor().email())));

        Curso curso = cursoRepository.findByNome(dados.curso().nome())
                .orElseGet(() -> cursoRepository.save(new Curso(null, dados.curso().nome(), "Categoria padrão")));

        topico.atualizarDados(dados, autor, curso);

        topicoRepository.save(topico);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}