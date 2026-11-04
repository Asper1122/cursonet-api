package es.github.asper1122.cursonet_api.dto.curso;

import jakarta.validation.constraints.NotBlank;

public record CreateCursoDto(
        @NotBlank(message = "El campo nombre no puede estar vacío")
        String nombre,
        String resumen
) {
}
