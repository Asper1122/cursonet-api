package es.github.asper1122.cursonet_api.controller.api;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Consulta de cursos registrados con paginación")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado registros correctamente"
            )
    })
    @GetMapping
    Page<CursoResponseDto> findAll(
            @Parameter(name = "page", example = "0", description = "Número de página para los cursos registrados")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(name = "size", example = "10", description = "Número de cursos registrados a consultar")
            @RequestParam(required = false, defaultValue = "10") int size);
}
