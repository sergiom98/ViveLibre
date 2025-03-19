package com.vivelibre.prueba.vivelibre.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Author {

    private String name;
    // Puede ser null
    private String firstSurname;
    private String bio;
}
