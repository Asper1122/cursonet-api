package es.github.asper1122.cursonet_api.service;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;

public interface CursoService {

    CursoDetailsResponseDto create(CreateCursoDto createCursoDto);
}
