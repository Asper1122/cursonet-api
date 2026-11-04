package es.github.asper1122.cursonet_api.controller.api;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso", description = "Métodos de gestión para cursos")
public interface CursoApi {

    @Operation(summary = "Creación de un nuevo curso")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se ha creado el curso correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CursoDetailsResponseDto.class),
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                                "nombre": "Curso de prueba",
                                                "resumen": "Curso para probar y esas cosas"
                                            }
                                            """)
                            }
                    )

            )
    })
    @PostMapping
    ResponseEntity<CursoDetailsResponseDto> createCurso(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateCursoDto.class),
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                        "nombre": "Curso de prueba",
                                                        "resumen": "Curso para probar y esas cosas"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody CreateCursoDto createCursoDto
    );
}
