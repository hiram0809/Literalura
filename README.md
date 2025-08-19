# LiterAlura - Catálogo Inteligente de Libros 📚

*LiterAlura* es una aplicación de consola que te permite explorar el mundo de los libros clásicos. Desarrollada en Java con Spring Boot, consume la API de Gutendex para convertir tu terminal en un catálogo literario interactivo con persistencia en base de datos.

## ✨ Características Principales
- 🔍 *Búsqueda inteligente de libros* por título (ej: "pride", "quixote")
- 📚 *Catálogo persistente* en base de datos PostgreSQL
- 👥 *Gestión de autores* con datos biográficos
- 🗓 *Filtro histórico*: Autores vivos en cualquier año
- 🌍 *Búsqueda por idioma* (español, inglés, francés, portugués)
- ⚠ *Validación anti-duplicados*: No permite registrar el mismo libro dos veces

## 🛠 Tecnologías Utilizadas
| Componente       | Tecnología           |
|------------------|----------------------|
| Lenguaje         | Java 24              |
| Framework        | Spring Boot 3.5.4    |
| Persistencia     | Spring Data JPA      |
| Base de Datos    | PostgreSQL           |
| Consumo API      | RestTemplate         |
| Gestión Dependencias | Maven             |

## 📦 Estructura del Proyecto

```text
literatura/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── alura/
│   │   │           └── literatura/
│   │   │               ├── Main.java             (Clase principal)
│   │   │               ├── model/                (Modelos de datos)
│   │   │               │   ├── Autor.java        (Entidad Autor)
│   │   │               │   └── Libro.java        (Entidad Libro)
│   │   │               ├── repository/           (Conexión con BD)
│   │   │               │   ├── AutorRepository.java  (Operaciones autores)
│   │   │               │   └── LibroRepository.java  (Operaciones libros)
│   │   │               ├── service/              (Lógica principal)
│   │   │               │   ├── APIService.java       (Consume API Gutendex)
│   │   │               │   └── MenuService.java     (Menú interactivo)
│   │   │               └── dto/                  (Objetos API)
│   │   │                   └── DatosLibro.java   (Mapeo JSON)
│   │   └── resources/
│   │       └── application.properties (Configuración PostgreSQL)
│   └── pom.xml                  (Dependencias Maven)
```


## 🚀 Instalación y Ejecución (Paso a Paso)

### 1. Crear base de datos en PostgreSQL
```sql
CREATE DATABASE literatura-1

Edita src/main/resources/application.properties con:

spring.datasource.url=jdbc:postgresql://localhost:tu_numero_de_server/literatura-1
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña;

======================================
        CATÁLOGO LITERALURA
======================================
1 - Buscar libro (ej: pride, quixote)
2 - Ver todos los libros
3 - Ver todos los autores
4 - Autores vivos en año (ej: 1850)
5 - Libros por idioma (es/en/fr/pt)
0 - Salir
======================================
