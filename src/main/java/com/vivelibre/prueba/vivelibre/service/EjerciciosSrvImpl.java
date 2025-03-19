package com.vivelibre.prueba.vivelibre.service;

import com.vivelibre.prueba.vivelibre.dto.*;
import com.vivelibre.prueba.vivelibre.model.Libro;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EjerciciosSrvImpl implements EjerciciosSrv {

    // Ejercicio 1
    @Override
    public List<Libro> filtrarLibrosMas400PaginasContengaHarry(List<Libro> libros, int numPaginas, String contenidoTitulo) {

        return libros.stream()
                .filter(libro -> libro.getPages() > numPaginas && libro.getTitle().contains(contenidoTitulo))
                .toList();
    }

    // Ejercicio 2
    @Override
    public List<Libro> filtrarLibrosPorNombreAutor(List<Libro> libros, String nombreAutor) {

        // Si no existen libros, devolvemos null
        if (libros == null || libros.isEmpty()) {
            return null;
        }

        List<Libro> librosFiltrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAuthor() != null && libro.getAuthor().getName().equals(nombreAutor)) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;

        // Alternativa usando Streams:
        /** <pre>
        return libros.stream()
                .filter(libro -> libro.getAuthor() != null && libro.getAuthor().getName().equals(nombreAutor))
                .collect(Collectors.toList()); </pre> */
    }

    // Ejercicio 3
    @Override
    public LibroOrdenadoContador listarTitulosOrdenadosPorAutor(List<Libro> libros) {

        // Si no existen libros, devolvemos null
        if (libros == null || libros.isEmpty()) {
            return null;
        }

        // 1️⃣ Obtener títulos ordenados alfabéticamente
        List<String> titulosOrdenados = libros.stream()
                .map(Libro::getTitle)
                .sorted(String::compareToIgnoreCase)
                .toList();

        // 2️⃣ Contador libros por autor
        Map<String, Long> contadorLibrosPorAutor = libros.stream()
                .filter(libro -> libro.getAuthor() != null && libro.getAuthor().getName() != null)
                .collect(Collectors.groupingBy(
                        libro -> libro.getAuthor().getName(),
                        Collectors.counting()
                ));

        return new LibroOrdenadoContador(titulosOrdenados, contadorLibrosPorAutor);
    }


    // Ejercicio 4
    @Override
    public List<LibroOtroFormatoTmst> convertirTimestampFormato(List<Libro> libros) {
        return libros.stream()
                .map(libro -> new LibroOtroFormatoTmst(libro.getId(),
                        libro.getTitle(),
                        convertirTimestampAFechaAAAAMMDDConGuiones(libro.getPublicationTimestamp()),
                        libro.getPages(),
                        libro.getSummary(),
                        libro.getAuthor()))
                .collect(Collectors.toList());
    }

    private String convertirTimestampAFechaAAAAMMDDConGuiones(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // Ejercicio 5
    @Override
    public String encontrarLibrosConMasMenosPaginas(List<Libro> libros) {

        // Si no existen libros, devolvemos null
        if (libros == null || libros.isEmpty()) {
            return null;
        }

        // Calcular el promedio de páginas
        double promedioPaginas = libros.stream()
                .filter(libro -> libro.getPages() != null)
                .mapToInt(Libro::getPages)
                .average()
                .orElse(0);

        // Encontrar el libro más cercano al promedio
        Libro libroMasCercanoAlPromedio = libros.stream()
                .filter(libro -> libro.getPages() != null)
                .min(Comparator.comparingDouble(libro -> Math.abs(libro.getPages() - promedioPaginas)))
                .orElse(null);

        // Si no se ha encontrado libro más cercano
        if (libroMasCercanoAlPromedio == null) {
            return MessageFormat.format("No se ha encontrado libro más cercano para el promedio {0}",
                    promedioPaginas);
        }

        // Libro más cercano al promedio encontrado
        return MessageFormat.format("El promedio es {0}, el libro más cercano al promedio es ''{1}'' con {2} páginas e id {3}",
                promedioPaginas, libroMasCercanoAlPromedio.getTitle(), libroMasCercanoAlPromedio.getPages(), libroMasCercanoAlPromedio.getId());
    }

    // Ejercicio 6
    @Override
    public Map<String, List<LibroConWordCount>> agruparLibrosPorAutorConWordCount(List<Libro> libros) {

        return libros.stream()
                .filter(libro -> libro.getAuthor() != null && libro.getAuthor().getName() != null)
                .map(LibroConWordCount :: libroConWordCount )
                .collect(Collectors.groupingBy(libro -> libro.author().getName()));
    }


    // Ejercicio 7 (Opcional)
    @Override
    public LibroAutoresFechas verificarAutoresDuplicadosYLibrosSinPublicationTimestamp(List<Libro> libros) {

        Set<String> autoresUnicos = new HashSet<>();
        Set<String> autoresDuplicados = new HashSet<>();
        List<Libro> librosSinPublicationTimestamp = new ArrayList<>();

        for (Libro libro : libros) {
            if (libro.getPublicationTimestamp() == null) {
                librosSinPublicationTimestamp.add(libro);
            }

            if (libro.getAuthor() != null && libro.getAuthor().getName() != null) {
                String nombreAutor = libro.getAuthor().getName();

                // Si ya estaba en autoresUnicos, es duplicado
                if (!autoresUnicos.add(nombreAutor)) {
                    autoresDuplicados.add(nombreAutor);
                }
            }
        }
        return new LibroAutoresFechas(autoresDuplicados, librosSinPublicationTimestamp);
    }

    // Ejercicio 8 (Opcional)
    @Override
    public List<Libro> identificarLibrosMasRecientes(List<Libro> libros) {

        // Encontrar el timestamp más reciente
        OptionalLong maxTimestamp = libros.stream()
                .filter(libro -> libro.getPublicationTimestamp() != null)
                .mapToLong(Libro::getPublicationTimestamp)
                .max();

        if (maxTimestamp.isEmpty()) {
            return List.of();
        }

        // Filtrar libros que tienen el timestamp más reciente
        long timestampMasReciente = maxTimestamp.getAsLong();
        return libros.stream()
                .filter(libro -> libro.getPublicationTimestamp() != null
                        && libro.getPublicationTimestamp() == timestampMasReciente)
                .collect(Collectors.toList());
    }

    @Override
    public String exportarLibrosCSV(List<Libro> libros) {
        String nombreArchivo = "libros_exportados.csv";
        final String saltoLinea = "\n";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Encabezado
            writer.append("id,title,author_name,pages");

            for (Libro libro : libros) {
                String title = libro.getTitle()!= null ? libro.getTitle() : "";
                String authorName = libro.getAuthor() != null ? libro.getAuthor().getName() : "";
                String pages = libro.getPages() != null ? libro.getPages().toString() : "";

                writer.append(saltoLinea + libro.getId() + "," + title  + "," + authorName + "," + pages);
            }

            return "Archivo CSV exportado: " + nombreArchivo;
        } catch (IOException e) {
            return "Error al exportar CSV: " + e.getMessage();
        }
    }

}
