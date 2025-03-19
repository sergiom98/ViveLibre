package com.vivelibre.prueba.vivelibre.dto;

import java.util.List;
import java.util.Map;

public record LibroOrdenadoContador(List<String> titulosOrdenados, Map<String, Long> contadorLibrosPorAutor) {
}
