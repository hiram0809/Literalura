package com.alura.literatura.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un libro.
 *
 * @author Angelo Kevin García Hernández
 */
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Double numeroDescargas;

    @ManyToOne
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, String idioma, Double numeroDescargas) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Double getNumeroDescargas() { return numeroDescargas; }
    public void setNumeroDescargas(Double numeroDescargas) { this.numeroDescargas = numeroDescargas; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}