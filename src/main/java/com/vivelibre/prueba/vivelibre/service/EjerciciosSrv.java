package com.vivelibre.prueba.vivelibre.service;

import com.vivelibre.prueba.vivelibre.dto.LibroAutoresFechas;
import com.vivelibre.prueba.vivelibre.dto.LibroConWordCount;
import com.vivelibre.prueba.vivelibre.dto.LibroOrdenadoContador;
import com.vivelibre.prueba.vivelibre.dto.LibroOtroFormatoTmst;
import com.vivelibre.prueba.vivelibre.model.Libro;

import java.util.List;
import java.util.Map;

public interface EjerciciosSrv {

    /**
     * Ejercicio 1. Filtra los libros con más de 400 páginas y aquellos cuyo título contenga "Harry".
     *
     * @param libros
     * @param numPaginas
     * @param contenidoTitulo
     * @return
     */
    List<Libro> filtrarLibrosMas400PaginasContengaHarry(List<Libro> libros, int numPaginas, String contenidoTitulo);

    /**
     * Ejercicio 2. Filtra los libros escritos por "J.K. Rowling".
     *
     * @param libros
     * @param nombreAutor
     * @return
     */
    List<Libro> filtrarLibrosPorNombreAutor(List<Libro> libros, String nombreAutor);


    /**
     * Ejercicio 3. Lista los títulos ordenados alfabéticamente y cuenta cuántos libros ha escrito cada autor.
     *
     * @param libros
     * @return
     */
    LibroOrdenadoContador listarTitulosOrdenadosPorAutor(List<Libro> libros);

    /**
     * Ejercicio 4. Convierte publicationTimestamp a formato AAAA-MM-DD.
     *
     * @param libros
     * @return
     */
    List<LibroOtroFormatoTmst> convertirTimestampFormato(List<Libro> libros);

    /**
     * Ejercicio 5. Calcula el promedio de páginas y devuelve el libro que más se aproxime.
     *
     * @param libros
     * @return
     */
    String encontrarLibrosConMasMenosPaginas(List<Libro> libros);

    /**
     * Ejercicio 6. Añade un campo wordCount 250 palabras por página y agrupa los libros por autor.
     *
     * @param libros
     * @return
     */
    Map<String, List<LibroConWordCount>> agruparLibrosPorAutorConWordCount(List<Libro> libros);

    /**
     * (Opcional) Ejercicio 7. Verifica si hay autores duplicados y encuentra los libros sin
     * publicationTimestamp.
     *
     * @param libros
     * @return
     */
    public LibroAutoresFechas verificarAutoresDuplicadosYLibrosSinPublicationTimestamp(List<Libro> libros);

    /**
     * (Opcional) Ejercicio 8. Identifica los libros más recientes.
     *
     * @param libros
     * @return
     */
    public List<Libro> identificarLibrosMasRecientes(List<Libro> libros);

    /**
     * (Opcional) Ejercicio 9. Genera un JSON con títulos y autores y exporta la lista a CSV
     * ( id, title, author_name, pages).
     *
     * @param libros
     * @return
     */
    public String exportarLibrosCSV(List<Libro> libros);
}
