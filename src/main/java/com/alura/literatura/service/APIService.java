package com.alura.literatura.service;

import com.alura.literatura.dto.DatosLibro;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {
    private final String API_URL = "https://gutendex.com/books?search=";

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void buscarLibroPorTitulo(String titulo) {
        // 1. Verificar si el libro YA EXISTE en la base de datos
        if (libroRepository.existsByTituloContainingIgnoreCase(titulo)) {
            System.out.println("\nNo se puede registrar el mismo libro más de una vez");
            return; // ¡Salimos del método aquí mismo!
        }

        // 2. Consumir la API si no existe
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + titulo.replace(" ", "%20");
        DatosLibro datos = restTemplate.getForObject(url, DatosLibro.class);

        if (datos == null || datos.results().isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }

        // 3. Procesar el primer resultado
        DatosLibro.Result primerResultado = datos.results().get(0);

        // 4. Crear y guardar el autor
        Autor autor = new Autor();
        if (!primerResultado.authors().isEmpty()) {
            autor.setNombre(primerResultado.authors().get(0).name());
            autor.setFechaNacimiento(primerResultado.authors().get(0).birth_year());
            autor.setFechaFallecimiento(primerResultado.authors().get(0).death_year());
        } else {
            autor.setNombre("Desconocido");
        }

        // 5. Crear y guardar el libro
        Libro libro = new Libro();
        libro.setTitulo(primerResultado.title());
        libro.setIdioma(!primerResultado.languages().isEmpty() ? primerResultado.languages().get(0) : "en");
        libro.setNumeroDescargas((double) primerResultado.download_count());
        libro.setAutor(autor);

        autorRepository.save(autor);
        libroRepository.save(libro);

        // 6. Mostrar resultados (como en tus capturas)
        System.out.println("\n--- LIBRO ---");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + autor.getNombre());
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Numero de descargas: " + libro.getNumeroDescargas());
        System.out.println("---");
    }
}