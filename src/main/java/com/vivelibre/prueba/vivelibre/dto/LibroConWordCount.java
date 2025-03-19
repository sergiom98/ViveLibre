package com.vivelibre.prueba.vivelibre.dto;

import com.vivelibre.prueba.vivelibre.model.Author;
import com.vivelibre.prueba.vivelibre.model.Libro;

public record LibroConWordCount(int id, String title, Long publicationTimestamp, Integer pages, int wordCount, String summary, Author author) {

    public static LibroConWordCount libroConWordCount(Libro libro) {
        int wordCount = libro.getPages() != null ? libro.getPages() * 250 : 0;
        return new LibroConWordCount(libro.getId(), libro.getTitle(), libro.getPublicationTimestamp(), libro.getPages(), wordCount, libro.getSummary(), libro.getAuthor());
    }
}
