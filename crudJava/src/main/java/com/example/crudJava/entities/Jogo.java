package com.example.crudJava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// A classe representa um jogo no sistema
@Entity
@Table(name = "jogos")
public class Jogo {
    // ID único do jogo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título do jogo
    private String titulo;

    // Relacionamento muitos-para-um com a entidade Editora
    @ManyToOne
    @JoinColumn(name = "editora_id")
    // Anotação para evitar referências em loop
    @JsonBackReference
    private Editora editora;

    // Construtor vazio padrão
    public Jogo() {
    }

    // Método getter para obter o ID do jogo
    public Long getId() {
        return id;
    }

    // Método setter para definir o ID do jogo
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obter o título do jogo
    public String getTitulo() {
        return titulo;
    }

    // Método setter para definir o título do jogo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Método getter para obter a editora do jogo
    public Editora getEditora() {
        return editora;
    }

    // Método setter para definir a editora do jogo
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
}