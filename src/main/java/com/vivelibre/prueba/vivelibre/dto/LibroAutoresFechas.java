package com.vivelibre.prueba.vivelibre.dto;

import com.vivelibre.prueba.vivelibre.model.Libro;

import java.util.List;
import java.util.Set;

public record LibroAutoresFechas(Set<String> autoresDuplicados, List<Libro> librosSinFecha) {
}
