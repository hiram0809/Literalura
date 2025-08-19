package com.alura.literatura;

import com.alura.literatura.service.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Literatura.
 * Implementa CommandLineRunner para ejecutar el menú al iniciar.
 *
 * @author Angelo Kevin García Hernández
 */
@SpringBootApplication
public class Main implements CommandLineRunner {
    private final MenuService menuService;

    public Main(MenuService menuService) {
        this.menuService = menuService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        menuService.mostrarMenu();
    }
}