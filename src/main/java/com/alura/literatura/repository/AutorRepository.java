package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repositorio para operaciones de base de datos con autores.
 *
 * @author Angelo Kevin García Hernández
 */
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Busca autores vivos en un año específico
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.fechaNacimiento AND a.fechaFallecimiento")
    List<Autor> findAutoresVivosEnAnio(Integer anio);
}