package com.example.crudJava.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// A classe representa uma editora de jogos no sistema
@Entity
@Table(name = "editora")
public class Editora {
    // ID único da editora
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da editora
    private String nome;

    // Relacionamento um-para-muitos com a entidade Jogo
    @OneToMany(mappedBy = "editora")
    /* Anotação para gerenciar referências no JSON e evitar referências
       em loop*/
    @JsonManagedReference
    private List<Jogo> jogos;

    // Construtor vazio padrão
    public Editora() {
    }

    // Método getter para obter o ID da editora
    public Long getId() {
        return id;
    }

    // Método setter para definir o ID da editora
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obter o nome da editora
    public String getNome() {
        return nome;
    }

    // Método setter para definir o nome da editora
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter para obter a lista de jogos associados à editora
    public List<Jogo> getJogos() {
        return jogos;
    }

    // Método setter para definir a lista de jogos associados À editora
    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
}