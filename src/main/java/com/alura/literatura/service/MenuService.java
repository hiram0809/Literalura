package com.alura.literatura.service;

import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

/**
 * Servicio que maneja la interfaz de usuario en consola.
 * Proporciona un menú interactivo con las opciones requeridas por el desafío LiterAlura.
 *
 * @author Angelo Kevin García Hernández
 */
@Service
public class MenuService {
    // Scanner para leer entrada del usuario
    private final Scanner scanner = new Scanner(System.in);

    // Servicio para consumir la API de Gutendex
    @Autowired
    private APIService apiService;

    // Repositorio para operaciones con libros
    @Autowired
    private LibroRepository libroRepository;

    // Repositorio para operaciones con autores
    @Autowired
    private AutorRepository autorRepository;

    /**
     * Muestra el menú principal y maneja las opciones del usuario.
     * El menú se ejecuta en bucle hasta que el usuario seleccione salir (opción 0).
     */
    public void mostrarMenu() {
        int opcion;
        do {
            // Mostrar opciones del menú
            System.out.println("\n======================================");
            System.out.println("        CATÁLOGO LITERALURA");
            System.out.println("======================================");
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar libros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos en un determinado año");
            System.out.println("5 - Listar libros por idioma");
            System.out.println("0 - Salir");
            System.out.println("======================================");
            System.out.print("Selección: ");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de entrada

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEnAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("\n¡Gracias por usar LiterAlura!");
                default -> System.out.println("\n❌ Opción inválida. Por favor ingrese un número del 0 al 5.");
            }
        } while (opcion != 0);
    }

    /**
     * Busca un libro por título consumiendo la API de Gutendex.
     * Maneja errores y muestra mensajes apropiados al usuario.
     */
    private void buscarLibroPorTitulo() {
        System.out.println("\n[ BUSCAR LIBRO POR TÍTULO ]");
        System.out.println("--------------------------------------");
        System.out.println("Sugerencias: pride | quixote | moby | frankenstein");
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        try {
            apiService.buscarLibroPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println("\n⚠️ Error: " + e.getMessage());
            System.out.println("Recomendación: Use títulos en inglés y términos cortos");
        }
    }

    /**
     * Lista todos los libros registrados en la base de datos.
     * Muestra un mensaje si no hay libros registrados.
     */
    private void listarLibrosRegistrados() {
        System.out.println("\n[ LIBROS REGISTRADOS ]");
        System.out.println("--------------------------------------");

        var libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el catálogo.");
            return;
        }

        libros.forEach(libro -> {
            System.out.println("\n--- LIBRO ---");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Descargas: " + libro.getNumeroDescargas());
            System.out.println("-----------------");
        });
    }

    /**
     * Lista todos los autores registrados y sus libros.
     * Muestra un mensaje si no hay autores registrados.
     */
    private void listarAutoresRegistrados() {
        System.out.println("\n[ AUTORES REGISTRADOS ]");
        System.out.println("--------------------------------------");

        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en el catálogo.");
            return;
        }

        autores.forEach(autor -> {
            System.out.println("\nAutor: " + autor.getNombre());
            System.out.println("Nacimiento: " +
                    (autor.getFechaNacimiento() != null ? autor.getFechaNacimiento() : "Desconocido"));
            System.out.println("Fallecimiento: " +
                    (autor.getFechaFallecimiento() != null ? autor.getFechaFallecimiento() : "Desconocido"));
            System.out.println("Libros:");

            if (autor.getLibros().isEmpty()) {
                System.out.println("  - Este autor no tiene libros registrados");
            } else {
                autor.getLibros().forEach(libro -> System.out.println("  - " + libro.getTitulo()));
            }
            System.out.println("-----------------");
        });
    }

    /**
     * Lista autores que estaban vivos en un año específico.
     * Solicita el año al usuario y muestra los resultados.
     */
    private void listarAutoresVivosEnAnio() {
        System.out.println("\n[ AUTORES VIVOS EN UN AÑO ]");
        System.out.println("--------------------------------------");
        System.out.print("Ingrese el año: ");

        try {
            int anio = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            var autores = autorRepository.findAutoresVivosEnAnio(anio);

            if (autores.isEmpty()) {
                System.out.println("\nNo se encontraron autores vivos en el año " + anio);
                return;
            }

            autores.forEach(autor -> {
                System.out.println("\nAutor: " + autor.getNombre());
                System.out.println("Nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println("-----------------");
            });
        } catch (Exception e) {
            System.out.println("\n⚠️ Error: Debe ingresar un año válido (ej: 1990)");
        }
    }

    /**
     * Lista libros por idioma.
     * Muestra un menú de idiomas y filtra los libros según la selección.
     */
    private void listarLibrosPorIdioma() {
        System.out.println("\n[ LIBROS POR IDIOMA ]");
        System.out.println("--------------------------------------");
        System.out.println("Códigos de idioma disponibles:");
        System.out.println("es - español");
        System.out.println("en - inglés");
        System.out.println("fr - francés");
        System.out.println("pt - portugués");
        System.out.print("Ingrese el código de idioma: ");

        String idioma = scanner.nextLine();
        var libros = libroRepository.findByIdioma(idioma.toLowerCase());

        if (libros.isEmpty()) {
            System.out.println("\nNo se encontraron libros en el idioma '" + idioma + "'");
            return;
        }

        System.out.println("\nLibros en " + idioma + ":");
        libros.forEach(libro -> {
            System.out.println("\n--- LIBRO ---");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("-----------------");
        });
    }
}