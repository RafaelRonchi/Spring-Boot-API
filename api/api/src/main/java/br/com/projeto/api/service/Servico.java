package br.com.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mysql.cj.protocol.x.Ok;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repository.Repositorio;

@Service
public class Servico {
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Método para cadastrar pessoas com validações
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("Nome vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else if(obj.getIdade() < 0) {
            mensagem.setMensagem("Idade menor que 0");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else{
            acao.save(obj);
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Metodo para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Metodo para selecionar pessoa pelo id ]
    public ResponseEntity<?> selecionarPeloId(int id){
        if(acao.countById(id) == 0){
            mensagem.setMensagem("Não existe regristo nesse id");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
        }
    }

    // metodo para editar dados 
    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countById(obj.getId()) == 0){
            mensagem.setMensagem("Id não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getNome().equals("")){
            mensagem.setMensagem("Nome vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() < 0){
            mensagem.setMensagem("Idade menor que 0");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    // Remover servicos 
    public ResponseEntity<?> remover(int id){
        if(acao.countById(id) == 0){
            mensagem.setMensagem("Id não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            Pessoa obj = acao.findById(id);
            acao.delete(obj);
            mensagem.setMensagem("Deletado");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
