package com.example.crudJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.crudJava.entities.Jogo;
import com.example.crudJava.Repositories.RepositorioJogo;

@RestController
@RequestMapping("/api/jogos")
public class ControllerJogo {
    // Injeção de dependência do repositório de Jogos
    @Autowired
    private RepositorioJogo repositorioJogo;

    // Retorna todos os jogos
    @GetMapping
    public Iterable<Jogo> getAllJogos() {
        // Obtém todos os jogos do repositório e os retorna
        return repositorioJogo.findAll();
    }

    // Retorna um jogo por ID
    @GetMapping("/{id}")
    public Jogo getJogoById(@PathVariable Long id) {
        // Busca um jogo pelo ID no repositório ou retorna nulo se não encontrado
        return repositorioJogo.findById(id).orElse(null);
    }

    // Cria um novo jogo
    @PostMapping
    public Jogo createJogo(@RequestBody Jogo jogo) {
        // Salva o novo jogo no repositório e o retorna
        return repositorioJogo.save(jogo);
    }

    // Atualiza um livro por ID
    @PutMapping("/{id}")
    public Jogo updateJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
        // Verifica se o jogo existe no repositório
        if (repositorioJogo.findById(id).orElse(null) == null) {
            return null;
        } else {
            // Define o ID do jogo e salva as alterações no repositório
            jogo.setId(id);
            return repositorioJogo.save(jogo);
        }
    }

    // Exclui um jogo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogo(@PathVariable Long id) {
        try {
            // Tenta excluir o jogo do repositório com base no ID
            repositorioJogo.deleteById(id);

            /* Retorna uma resposta com código 204 (No Content) e corpo vazio para
               indicar exclusão bem-sucedida */
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {

            /* Se o jogo não for encontrado, retorna um código de status 404 (Not
               Found) */
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            
            /* Se ocorrer uma exceção inesperada, retorna um código de status 500
               (Internal Server Error) */
            return ResponseEntity.status(500).build();
        }
    }

}
