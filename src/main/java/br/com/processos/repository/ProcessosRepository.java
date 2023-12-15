package br.com.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.processos.model.Processos;


@Repository
public interface ProcessosRepository extends JpaRepository<Processos, Integer> {

    @Query(value = "select p from Processos p where p.numProcessos=?1")
    Processos findByNumProcessos(String numProcessos);
} 
