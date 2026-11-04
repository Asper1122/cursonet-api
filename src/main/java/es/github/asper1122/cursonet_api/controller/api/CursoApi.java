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
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


    @Operation(summary = "Consulta de los detalles de un curso")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se ha encontrado los detalles correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CursoDetailsResponseDto.class),
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                                "id": "a38723ec-03ab-4364-a0cb-ab7de6ea150d",
                                                "nombre": "Fundamentos de la programación",
                                                "resumen": "En este curso aprenderás los fundamentos básicos de la programación, como la orientación a objetos, y las patate y\\n las burgueeses"
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se han encontrado los datos a consultar",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                                "detail": "No se ha encontrado el curso con el id 96c52fc6-0f6c-49db-a911-9b77cb70d0da",
                                                "instance": "/curso/96c52fc6-0f6c-49db-a911-9b77cb70d0da",
                                                "status": 404,
                                                "title": "Entidad no encontrada",
                                                "type": "http://cursonet.com/errors/not-found",
                                                "timestamp": "2026-11-04T02:59:14.359635700Z"
                                            }
                                            """)
                            }
                    )
            )
    })
    @GetMapping("/{id}")
    CursoDetailsResponseDto findById(@PathVariable UUID id);
}
