package com.vivelibre.prueba.vivelibre.controller;

import com.vivelibre.prueba.vivelibre.dto.LibroAutoresFechas;
import com.vivelibre.prueba.vivelibre.dto.LibroConWordCount;
import com.vivelibre.prueba.vivelibre.dto.LibroOrdenadoContador;
import com.vivelibre.prueba.vivelibre.dto.LibroOtroFormatoTmst;
import com.vivelibre.prueba.vivelibre.model.Libro;
import com.vivelibre.prueba.vivelibre.service.EjerciciosSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/ejercicios")
@RequiredArgsConstructor
public class EjerciciosController {

    private static final String ERROR_LIBROS_NO_INDICADOS = "No se han indicado libros en la petición.";

    private final EjerciciosSrv ejerciciosSrv;

    @PostMapping("/filtrar-libros-mas-400-paginas-contenga-harry")
    public ResponseEntity<List<Libro>> filtrarLibrosMas400PaginasContengaHarry(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.filtrarLibrosMas400PaginasContengaHarry(libros, 400, "Harry"));
    }

    @PostMapping("/filtrar-libros-por-nombre-autor")
    public ResponseEntity<List<Libro>> listarTitulosOrdenadosPorAutor(@RequestBody List<Libro> libros, @RequestParam String nombreAutor) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(List.of());
        }

        return ResponseEntity.ok(ejerciciosSrv.filtrarLibrosPorNombreAutor(libros, nombreAutor));
    }

    @PostMapping("/libros-por-autor-ordenados-alfabeticamente")
    public ResponseEntity<LibroOrdenadoContador> listarTitulosOrdenadosPorAutor(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.listarTitulosOrdenadosPorAutor(libros));
    }

    @PostMapping("/convertir-timestamp-formato")
    public ResponseEntity<List<LibroOtroFormatoTmst>> convertirTimestampFormatoYYYYMMDDconGuiones(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.convertirTimestampFormato(libros));
    }
    @PostMapping("/encontrar-libro-con-mas-menos-paginas")
    public ResponseEntity<String> encontrarLibrosConMasMenosPaginas(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.badRequest().body(ERROR_LIBROS_NO_INDICADOS);
        }
        return ResponseEntity.ok(ejerciciosSrv.encontrarLibrosConMasMenosPaginas(libros));
    }
    @PostMapping("/agrupar-por-autor-con-word-count")
    public ResponseEntity<Map<String, List<LibroConWordCount>>> agruparLibrosPorAutorConWordCount(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.agruparLibrosPorAutorConWordCount(libros));
    }

    @PostMapping("/verificar-autores-y-fechas")
    public ResponseEntity<LibroAutoresFechas> verificarAutoresDuplicadosYLibrosSinFecha(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.verificarAutoresDuplicadosYLibrosSinPublicationTimestamp(libros));
    }

    @PostMapping("/libros-mas-recientes")
    public ResponseEntity<List<Libro>> identificarLibrosMasRecientes(@RequestBody List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ejerciciosSrv.identificarLibrosMasRecientes(libros));
    }

    @PostMapping("/exportar-csv")
    public ResponseEntity<FileSystemResource> exportarLibrosCSV(@RequestBody List<Libro> libros) {
        String nombreArchivo = ejerciciosSrv.exportarLibrosCSV(libros);
        FileSystemResource archivo = new FileSystemResource("libros_exportados.csv");

        if (!archivo.exists()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=libros_exportados.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(archivo);
    }

}
