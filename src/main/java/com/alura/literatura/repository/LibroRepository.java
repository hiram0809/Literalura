package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de base de datos con libros.
 *
 * @author Angelo Kevin García Hernández
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Busca libros que contengan el título (ignorando mayúsculas/minúsculas)
    Optional<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Busca libros por idioma
    List<Libro> findByIdioma(String idioma);

    // Verifica si existe un libro con el título
    boolean existsByTituloContainingIgnoreCase(String titulo);
}