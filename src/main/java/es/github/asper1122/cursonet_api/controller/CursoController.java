package es.github.asper1122.cursonet_api.controller;

import es.github.asper1122.cursonet_api.controller.api.CursoApi;
import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController implements CursoApi {

    private final CursoService cursoService;

    @Override
    public ResponseEntity<CursoDetailsResponseDto> createCurso(CreateCursoDto createCursoDto) {

        CursoDetailsResponseDto response = cursoService.create(createCursoDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(response.id());

        return ResponseEntity.created(location).body(response);
    }
}
