package es.github.asper1122.cursonet_api.dto.curso;

import java.util.UUID;

public record CursoResponseDto(
        UUID id,
        String nombre
) {
}
