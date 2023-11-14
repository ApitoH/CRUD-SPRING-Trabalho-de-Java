package com.example.crudJava.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudJava.entities.Jogo;

public interface RepositorioJogo extends JpaRepository<Jogo, Long> {

}