package com.vivelibre.prueba.vivelibre.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Libro {

    private int id;
    private String title;
    // Puede ser null
    private Long publicationTimestamp;
    private Integer pages;
    private String summary;
    private Author author;
}
