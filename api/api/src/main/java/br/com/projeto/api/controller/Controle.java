package br.com.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repository.Repositorio;
import br.com.projeto.api.service.Servico;
import jakarta.validation.Valid;

@RestController
public class Controle {
    @Autowired
    private Repositorio acao;

    @Autowired Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa objPessoa){
        return servico.cadastrar(objPessoa);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> remover(@PathVariable int id){
      return servico.remover(id); 
    }

    @PostMapping("/pessoa")
    public Pessoa p(@RequestBody Pessoa pessoa){
        return pessoa;
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<?> selecionarPeloId(@PathVariable int id){
        return servico.selecionarPeloId(id);
    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenaNome(){
        return acao.findByOrderByNomeAsc();
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("Rafael");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("Ra");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("A");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("L");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaioIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(18);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }


    @GetMapping("")
    public String mensagem(){
        return "Hello World";
    }

    @GetMapping("/boasVindas/{nome}")
    public String ola(@PathVariable String nome){
        return "aaaa "+ nome;
    }

}
