package com.example.crudJava.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudJava.entities.Editora;

public interface RepositorioEditora extends JpaRepository<Editora, Long> {
    /*  RepositorioEditora é uma interface que estende JpaRepository, que é fornecida
    	pelo Spring Data JPA.
    	Essa interface fornece métodos de acesso aos dados do tipo Editora no banco de
    	dados.

    	Editora é o tipo de entidade com o qual essa interface está associada.

    	Long especifica o tipo de dados do ID da entidade, que neste caso é Editora.
    	Ou seja, os IDs das editoras são do tipo Long.

    	Com essa interface, é possível ter acesso a métodos como save, findById, findAll,
    	delete, entre outros,
    	para realizar operações de CRUD no banco de dados associadas à entidade
    	Editora.
    	O Spring Data JPA implementa esses métodos automaticamente com base nas
    	convenções de nomenclatura e mapeamento da entidade.*/
}