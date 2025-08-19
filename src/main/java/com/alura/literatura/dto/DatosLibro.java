package com.alura.literatura.dto;

import java.util.List;

public record DatosLibro(
        int count,
        String next,
        String previous,
        List<Result> results
) {
    public record Result(
            String title,
            List<Author> authors,
            List<String> languages,
            int download_count
    ) {}

    public record Author(
            String name,
            Integer birth_year,
            Integer death_year
    ) {}
}