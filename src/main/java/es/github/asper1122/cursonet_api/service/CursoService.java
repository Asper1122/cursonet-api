package es.github.asper1122.cursonet_api.service;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoResponseDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CursoService {

    CursoDetailsResponseDto create(CreateCursoDto createCursoDto);

    Page<CursoResponseDto> findAll(int page, int size);

    CursoDetailsResponseDto findById(UUID id);

    CursoDetailsResponseDto update(UUID id, CreateCursoDto createCursoDto);

    void deleteById(UUID id);
}
