package com.example.crudJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.crudJava.entities.Editora;
import com.example.crudJava.entities.Jogo;
import com.example.crudJava.Repositories.RepositorioEditora;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/editoras")
public class ControllerEditora {
    // Injeção de dependência do Repositorio de Editoras
    @Autowired
    private RepositorioEditora repositorioEditora;

    // Retorna todos as editoras
    @GetMapping
    public Iterable<Editora> getAllEditoras() {
        // Obtém todos as editoras do repositório
        Iterable<Editora> editoras = repositorioEditora.findAll();
        // Para cada editora, carrega a lista de jogos associados a ela
        for (Editora editora : editoras) {
            editora.setJogos(editora.getJogos());
        }
        // Retorna a lista de editoras
        return editoras;
    }

    // Retorna uma editora específica através do ID
    @GetMapping("/{id}")
    public Editora getEditoraById(@PathVariable Long id) {
        // Busca uma editora pelo ID no repositório ou retorna nulo se não encontrado
        return repositorioEditora.findById(id).orElse(null);
    }

    // Cria uma nova editora
    @PostMapping
    public Editora createEditora(@RequestBody Editora editora) {
        // Salva a nova editora no repositório e a retorna
        return repositorioEditora.save(editora);
    }

    // Atualiza uma editora por ID
    @PutMapping("/{id}")
    public Editora updateEditora(@PathVariable Long id, @RequestBody Editora editora) {
        // Verifica se a editora existe no repositório
        if (repositorioEditora.findById(id).orElse(null) == null) {
            return null;
        } else {
            // Define o ID da editora e salva as alterações no repositório
            editora.setId(id);
            return repositorioEditora.save(editora);
        }
    }

    // Exclui a editora por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditora(@PathVariable Long id) {
        // Verifica se a editora existe
        Editora editora = repositorioEditora.findById(id).orElse(null);

        if (editora != null) {
            try {
                // Desassocia a editora dos jogos
                List<Jogo> jogos = editora.getJogos();
                for (Jogo jogo : jogos) {
                    jogo.setEditora(null);
                }

                // Exclui a editora do repositório
                repositorioEditora.delete(editora);

                /* Retorna uma resposta com código 204 (No Content) e corpo vazio para
                   indicar exclusão bem-sucedida */
                return ResponseEntity.noContent().build();

            } catch (Exception e) {
                
                /* Se ocorrer uma exceção inesperada, retorne um código de status 500
                   (Internal Server Error) */
                return ResponseEntity.status(500).build();
            }
        } else {
            /* Se a editora não for encontrada, retorne um código de status 404 (Not
               Found) */
            return ResponseEntity.notFound().build();
        }
    }
}
